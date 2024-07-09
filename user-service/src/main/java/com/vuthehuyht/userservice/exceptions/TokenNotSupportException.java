package com.vuthehuyht.userservice.exceptions;

import com.vuthehuyht.userservice.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenNotSupportException extends RuntimeException {
    private ErrorCode errorCode;

    public TokenNotSupportException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
