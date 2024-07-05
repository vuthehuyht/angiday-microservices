package com.vuthehuyht.user_service.services;

import com.vuthehuyht.user_service.requests.LoginRequest;
import com.vuthehuyht.user_service.requests.RegisterRequest;
import com.vuthehuyht.user_service.responses.LoginResponse;
import com.vuthehuyht.user_service.responses.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
