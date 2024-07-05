package com.vuthehuyht.user_service.exceptions;

import com.vuthehuyht.user_service.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenExpiredException extends RuntimeException {
    private ErrorCode errorCode;

    public TokenExpiredException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
