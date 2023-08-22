package com.example.miniproject.global.loginLog.repository;

import com.example.miniproject.global.loginLog.domain.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {
}
