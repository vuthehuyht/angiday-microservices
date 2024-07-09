package com.vuthehuyht.userservice.services;

import com.vuthehuyht.userservice.requests.LoginRequest;
import com.vuthehuyht.userservice.requests.RegisterRequest;
import com.vuthehuyht.userservice.responses.LoginResponse;
import com.vuthehuyht.userservice.responses.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
