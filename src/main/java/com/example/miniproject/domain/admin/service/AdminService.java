package com.example.miniproject.domain.admin.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.miniproject.domain.admin.dto.AdminResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.miniproject.domain.admin.dto.AdminRequestDto;
import com.example.miniproject.domain.annual.domain.Annual;
import com.example.miniproject.domain.annual.repository.AnnualRepository;
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
public class AdminService {
	private final AnnualRepository annualRepository;
	private final MemberRepository memberRepository;
	private final EntityManager entityManager;

	public List<AdminResponseDto.MainDto> getAnnuals() {
		List<Annual> annuals = annualRepository.findAll();

		List<AdminResponseDto.MainDto> mainDtos = annuals.stream()
			.map(annual -> new AdminResponseDto.MainDto(annual))
			.toList();

		return mainDtos;
	}

	@Transactional
	public boolean updateStatus(AdminRequestDto.ApplyDto applyDto, String email) {
		Annual annual = annualRepository.findById(applyDto.getId())
			.orElseThrow(() -> new AnnualException(ErrorCode.ANNUAL_NOT_FOUND));

		annual.setStatus(Status.COMPLETE);

		LocalDateTime startLocalDate = annual.getStartedAt().atStartOfDay();
		LocalDateTime endLocalDate = annual.getLastedAt().atStartOfDay();

		int annualDays = DateUtil.getDateDiff(startLocalDate, endLocalDate);

		updateAnnualAmount(annual.getMember().getEmail(), annualDays);

		return true;
	}

	private void updateAnnualAmount(String email, int annualDays) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));

		member.sumAnnualRemain(annualDays * -1);
		member.sumAnnualUsed(annualDays);
	}
}
