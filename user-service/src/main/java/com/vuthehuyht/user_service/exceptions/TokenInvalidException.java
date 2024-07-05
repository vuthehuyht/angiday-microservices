package com.vuthehuyht.user_service.exceptions;

import com.vuthehuyht.user_service.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenInvalidException extends RuntimeException {
    private ErrorCode errorCode;

    public TokenInvalidException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
