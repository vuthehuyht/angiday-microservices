package com.vuthehuyht.userservice.exceptions;

import com.vuthehuyht.userservice.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsernameOrPasswordWrongException extends RuntimeException {
    private ErrorCode errorCode;

    public UsernameOrPasswordWrongException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
