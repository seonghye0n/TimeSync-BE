package com.example.miniproject.domain.annual.dto;

import com.example.miniproject.domain.annual.domain.Annual;
import com.example.miniproject.global.annotation.AfterStartDate;
import com.example.miniproject.global.constant.Category;
import com.example.miniproject.global.constant.ErrorCode;
import com.example.miniproject.global.constant.Reason;
import com.example.miniproject.global.constant.Status;
import com.example.miniproject.domain.member.domain.Member;
import com.example.miniproject.global.error.exception.AnnualException;
import com.example.miniproject.global.util.DateUtil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.cglib.core.Local;

public class AnnualRequestDto {
    @Getter
    @Setter
    @AfterStartDate
    public static class SaveDto {
        @NotBlank(message = "제목은 비워둘 수 없습니다.")
        private String title;
        @NotBlank(message = "연차/당직 구분은 비워둘 수 없습니다.")
        private String category;
        @NotBlank(message = "시작일을 비워둘 수 없습니다.")
        private String startDate;
        @NotBlank(message = "종료일을 비워둘 수 없습니다.")
        private String endDate;
        private String reason;

        public Annual toEntity(Member member) {
            return Annual.builder()
                    .title(this.title)
                    .category(Category.findByName(this.category))
                    .startedAt(DateUtil.parseLocalDate(this.startDate))
                    .lastedAt(DateUtil.parseLocalDate(this.endDate))
                    .reason(Reason.findByName(this.reason))
                    .member(member)
                    .status(Status.READY)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class CancelDto {
        @NotNull
        private Long id;
    }

    @Getter
    @Setter
    @AfterStartDate
    public static class UpdateDto {
        @NotNull
        private Long id;
        @NotBlank
        private String title;
        @NotBlank
        private String startDate;
        @NotBlank
        private String endDate;
        private String reason;
    }

    @Getter
    @Setter
    public static class UpdatePasswordDto {

        @NotBlank(message = "패스워드는 비워둘 수 없습니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$"
            , message = "비밀번호는 8글자 ~ 16글자 로 대소문자 1개, 숫자 1개, 특수문자 1개 이상으로 조합해야 합니다.")
        private String newPassword;

    }
}
