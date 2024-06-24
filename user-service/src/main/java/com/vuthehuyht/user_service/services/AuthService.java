package com.vuthehuyht.user_service.services;

import com.vuthehuyht.user_service.requests.RegisterRequest;
import com.vuthehuyht.user_service.responses.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);
}
