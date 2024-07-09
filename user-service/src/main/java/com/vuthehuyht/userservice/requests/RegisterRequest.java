package com.vuthehuyht.userservice.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vuthehuyht.userservice.annotations.AtLeast8Character;
import com.vuthehuyht.userservice.annotations.CheckDateFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

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