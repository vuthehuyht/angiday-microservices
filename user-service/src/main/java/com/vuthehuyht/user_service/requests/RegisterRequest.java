package com.vuthehuyht.user_service.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vuthehuyht.user_service.annotations.AtLeast8Character;
import com.vuthehuyht.user_service.annotations.CheckDateFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "USERNAME_REQUIRED")
    private String username;

    @NotBlank(message = "PASSWORD_REQUIRED")
    @AtLeast8Character
    private String password;

    @NotBlank(message = "FULLNAME_REQUIRED")
    @JsonProperty("full_name")
    private String fullName;

    @NotBlank(message = "GENDER_REQUIRED")
    private String gender;

    @NotBlank(message = "BIRTHDAY_REQUIRED")
    @CheckDateFormat(pattern = "dd/MM/YYYY")
    private String birthday;
}