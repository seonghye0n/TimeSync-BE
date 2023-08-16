package com.example.miniproject.global.loginLog.service;

import com.example.miniproject.global.loginLog.domain.LoginLog;
import com.example.miniproject.global.loginLog.repository.LoginLogRepository;
import com.example.miniproject.global.loginLog.dto.LoginLogDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginLogService {

    private final LoginLogRepository loginLogRepository;

    // 로그인 로그 저장
    public void save(LoginLogDto.CreateLoginLog createLoginLog) {
        LoginLog loginLog = createLoginLog.toEntity();
        loginLogRepository.save(loginLog);
    }

}
