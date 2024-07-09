package com.vuthehuyht.userservice.exceptions;

import com.vuthehuyht.userservice.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsernameExistException extends RuntimeException {
    private ErrorCode errorCode;

    public UsernameExistException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
