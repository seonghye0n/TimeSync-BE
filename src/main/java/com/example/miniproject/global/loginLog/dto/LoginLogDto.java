package com.example.miniproject.global.loginLog.dto;

import com.example.miniproject.global.loginLog.domain.LoginLog;
import com.example.miniproject.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class LoginLogDto {

    @Getter
    @Builder
    public static class CreateLoginLog {

        private Long userId;

        private Member member;

        private String userAgent;

        private String clientIp;

        private LocalDateTime successLoginDate;

        public LoginLog toEntity() {
            return LoginLog.builder()
                    .id(userId)
                    .member(member)
                    .userAgent(userAgent)
                    .clientIp(clientIp)
                    .successLoginDate(successLoginDate)
                    .build();
        }

    }

}
