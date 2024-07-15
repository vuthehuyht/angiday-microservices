package com.vuthehuyht.userservice.controllers;

import com.vuthehuyht.userservice.requests.IntrospectRequest;
import com.vuthehuyht.userservice.requests.LoginRequest;
import com.vuthehuyht.userservice.requests.RegisterRequest;
import com.vuthehuyht.userservice.responses.ApiResponse;
import com.vuthehuyht.userservice.responses.IntrospectResponse;
import com.vuthehuyht.userservice.responses.LoginResponse;
import com.vuthehuyht.userservice.responses.RegisterResponse;
import com.vuthehuyht.userservice.services.AuthService;
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

    @PostMapping(path = "/login")
    ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        log.info("Handling user login");
        return ApiResponse.<LoginResponse>builder()
                .code(HttpStatus.OK.value())
                .data(authService.login(request))
                .build();
    }

    @PostMapping(path = "/introspect")
    ApiResponse<IntrospectResponse> introspect(@Valid @RequestBody IntrospectRequest request) {
        log.info("Checking token validity");
        return ApiResponse.<IntrospectResponse>builder()
                .code(HttpStatus.OK.value())
                .data(authService.introspect(request))
                .build();
    }
}
