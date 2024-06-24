package com.vuthehuyht.user_service.controllers;

import com.vuthehuyht.user_service.requests.RegisterRequest;
import com.vuthehuyht.user_service.responses.ApiResponse;
import com.vuthehuyht.user_service.responses.RegisterResponse;
import com.vuthehuyht.user_service.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/register")
    ApiResponse<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        log.info("Intercept registration new user");
        return ApiResponse.<RegisterResponse>builder()
                .code(HttpStatus.OK.value())
                .data(authService.register(request))
                .build();
    }
}
