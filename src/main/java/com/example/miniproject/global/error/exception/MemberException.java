package com.example.miniproject.global.error.exception;

import com.example.miniproject.global.constant.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberException extends DomainException {

    private ErrorCode errorCode;

    public MemberException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
