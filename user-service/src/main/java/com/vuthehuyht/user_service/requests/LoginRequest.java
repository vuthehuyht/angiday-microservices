package com.vuthehuyht.user_service.requests;

import com.vuthehuyht.user_service.annotations.AtLeast8Character;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "USERNAME_REQUIRED")
    private String username;

    @NotBlank(message = "PASSWORD_REQUIRED")
    @AtLeast8Character
    private String password;
}
