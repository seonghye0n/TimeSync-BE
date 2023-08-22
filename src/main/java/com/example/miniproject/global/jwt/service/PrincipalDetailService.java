package com.example.miniproject.global.jwt.service;

import com.example.miniproject.global.constant.ErrorCode;
import com.example.miniproject.global.error.exception.MemberException;
import com.example.miniproject.domain.member.domain.Member;
import com.example.miniproject.domain.member.domain.PrincipalDetails;
import com.example.miniproject.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));

        return new PrincipalDetails(member);
    }


}
