package com.example.miniproject.exception;

import com.example.miniproject.constant.ErrorCode;
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
