package com.example.miniproject.global.error.exception;

import com.example.miniproject.global.constant.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnnualException extends DomainException {

    private ErrorCode errorCode;

    public AnnualException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
