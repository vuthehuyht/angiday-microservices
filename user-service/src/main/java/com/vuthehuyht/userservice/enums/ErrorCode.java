package com.vuthehuyht.userservice.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(1000, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),

    // Validation error code
    USERNAME_REQUIRED(1002, "Username is required", HttpStatus.BAD_REQUEST),
    PASSWORD_REQUIRED(1003, "Password is required", HttpStatus.BAD_REQUEST),
    FULLNAME_REQUIRED(1004, "Full name is required", HttpStatus.BAD_REQUEST),
    GENDER_REQUIRED(1005, "Gender is required", HttpStatus.BAD_REQUEST),
    BIRTHDAY_REQUIRED(1006, "Birthday is required", HttpStatus.BAD_REQUEST),
    AT_LEAST_8_CHARACTERS(1007, "Password at least 8 characters", HttpStatus.BAD_REQUEST),
    DATE_FORMAT_ERROR(1008, "Date should be dd/MM/YYYY or dd/MM/yy", HttpStatus.BAD_REQUEST),
    TOKEN_REQUIRED(1009, "Access token is required", HttpStatus.BAD_REQUEST),

    // Exception
    USERNAME_EXIST(600, "Username {} exist", HttpStatus.BAD_REQUEST),
    TOKEN_NOT_SUPPORT(601, "Token not support", HttpStatus.UNAUTHORIZED),
    TOKEN_INVALID(602, "Token is invalid format", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(603, "Token is expired", HttpStatus.UNAUTHORIZED),
    UNKNOWN_ERROR(604, "Unknown error", HttpStatus.UNAUTHORIZED),
    USERNAME_NOTFOUND(605, "Username not found", HttpStatus.NOT_FOUND),
    USERNAME_PASSWORD_WRONG(606, "Username or password wrong", HttpStatus.BAD_REQUEST)
    ;

    ErrorCode(Integer code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final Integer code;
    private final String message;
    private final HttpStatusCode statusCode;
}
