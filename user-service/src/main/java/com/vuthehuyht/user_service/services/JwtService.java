package com.vuthehuyht.user_service.services;

import com.vuthehuyht.user_service.models.User;
import io.jsonwebtoken.Claims;

import java.security.Key;

public interface JwtService {
    Claims extractClaims(String token);

    Key getKey();

    String generateToken(User user);

    boolean isValidToken(String token);
}
