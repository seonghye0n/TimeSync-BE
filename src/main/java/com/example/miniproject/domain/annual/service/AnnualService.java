package com.example.miniproject.domain.annual.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.miniproject.domain.annual.repository.AnnualRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.miniproject.domain.annual.domain.Annual;
import com.example.miniproject.domain.annual.dto.AnnualRequestDto;
import com.example.miniproject.domain.annual.dto.AnnualResponseDto;
import com.example.miniproject.global.constant.Category;
import com.example.miniproject.global.constant.ErrorCode;
import com.example.miniproject.global.constant.Status;
import com.example.miniproject.global.error.exception.AnnualException;
import com.example.miniproject.global.error.exception.MemberException;
import com.example.miniproject.domain.member.domain.Member;
import com.example.miniproject.domain.member.repository.MemberRepository;
import com.example.miniproject.global.util.DateUtil;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnnualService {
	private final AnnualRepository annualRepository;
	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final EntityManager entityManager;

	public AnnualResponseDto.MainDto findAll(String email) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberException(ErrorCode.TOKEN_NOT_FOUND));

		List<Annual> annuals = annualRepository.findAllByStatus(Status.COMPLETE);

		return new AnnualResponseDto.MainDto(member.getName(), annuals);
	}

	@Transactional
	public void createAnnual(AnnualRequestDto.SaveDto saveDto, String email) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));

		if (isExistAnnual(saveDto.getStartDate(), saveDto.getEndDate(), member))
			throw new AnnualException(ErrorCode.ANNUAL_DATE_DUPLICATED);

		if (saveDto.getCategory().equals(Category.ANNUAL.getName())) {
			if (isNotRemainAnnualAmount(saveDto.getStartDate(), saveDto.getEndDate(), member.getAnnualRemain()))
				throw new AnnualException(ErrorCode.ANNUAL_NOT_BALANCED_AMOUNT);
		}

		Annual annual = saveDto.toEntity(member);

		annualRepository.save(annual);
	}

	public void findAllByMember(String email) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));

		List<Annual> annuals = annualRepository.findAllByMemberAndCategory(member, Category.ANNUAL);
		List<Annual> duties = annualRepository.findAllByMemberAndCategory(member, Category.DUTY);

	}

	@Transactional
	public void deleteAnnual(AnnualRequestDto.CancelDto cancelDto, String email) {
		Annual annual = annualRepository.findById(cancelDto.getId())
			.orElseThrow(() -> new AnnualException(ErrorCode.ANNUAL_NOT_FOUND));

		if (annual.getStatus() == Status.COMPLETE)
			throw new AnnualException(ErrorCode.ANNUAL_NOT_MODIFY_CANCEL);

		if (annual.getMember().isNotEqualsEmail(email))
			throw new AnnualException(ErrorCode.MEMBER_NOT_MATCHED);

		annualRepository.delete(annual);
	}

	@Transactional
	public void updateAnnual(AnnualRequestDto.UpdateDto updateDto, String email) {
		Annual annual = annualRepository.findById(updateDto.getId())
			.orElseThrow(() -> new AnnualException(ErrorCode.ANNUAL_NOT_FOUND));

		if (annual.getMember().isNotEqualsEmail(email))
			throw new MemberException(ErrorCode.MEMBER_NOT_MATCHED);

		if (annual.getCategory().equals(Category.ANNUAL.getName())) {
			if (isNotRemainAnnualAmount(updateDto.getStartDate(), updateDto.getEndDate(),
				annual.getMember().getAnnualRemain()))
				throw new AnnualException(ErrorCode.ANNUAL_NOT_BALANCED_AMOUNT);
		}

		if (isExistAnnual(updateDto.getStartDate(), updateDto.getEndDate(), annual.getMember()))
			throw new AnnualException(ErrorCode.ANNUAL_DATE_DUPLICATED);

		annual.updateData(updateDto);
	}

	public AnnualResponseDto.MyPageDto findAnnualsByMember(String email) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));

		List<Annual> annuals = findAllByMemberAndCategory(member, Category.ANNUAL);
		List<Annual> duties = findAllByMemberAndCategory(member, Category.DUTY);

		List<AnnualResponseDto.AnnualHistroy> annualHistroys = annuals.stream()
			.map(annual -> new AnnualResponseDto.AnnualHistroy(annual))
			.collect(
				Collectors.toList());

		List<AnnualResponseDto.DutyHistory> dutyHistories = duties.stream()
			.map(duty -> new AnnualResponseDto.DutyHistory(duty))
			.collect(
				Collectors.toList());
		;

		return new AnnualResponseDto.MyPageDto(member, annualHistroys, dutyHistories);
	}

	@Transactional
	public void updatePassword(AnnualRequestDto.UpdatePasswordDto updatePasswordDto, String email) {

		// 이메일로 회원의 정보 찾기
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));

		// 회원의 비밀번호를 암호화 한 후에 저장
		member.changePassword(passwordEncoder.encode(updatePasswordDto.getNewPassword()));

	}

	private List<Annual> findAllByMemberAndCategory(Member member, Category category) {
		List<Annual> annuals = annualRepository.findAllByMemberAndCategory(member, category);

		return annuals;
	}

	private boolean isExistAnnual(String startDate, String endDate, Member member) {
		LocalDate startLocalDate = LocalDate.parse(startDate);
		LocalDate endLocalDate = LocalDate.parse(endDate);

		Long count = annualRepository.countByStartedAtAndLastedAt(startLocalDate, endLocalDate, member);

		return count > 0;
	}

	private boolean isNotRemainAnnualAmount(String startDate, String endDate, int annualRemain) {
		LocalDateTime startLocalDate = LocalDate.parse(startDate).atStartOfDay();
		LocalDateTime endLocalDate = LocalDate.parse(endDate).atStartOfDay();

		int betweenDays = DateUtil.getDateDiff(startLocalDate, endLocalDate);

		return annualRemain - betweenDays < 0;
	}
}
