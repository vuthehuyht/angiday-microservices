package com.vuthehuyht.userservice.services;

import com.vuthehuyht.userservice.models.User;
import io.jsonwebtoken.Claims;

import java.security.Key;

public interface JwtService {
    Claims extractClaims(String token);

    Key getKey();

    String generateToken(User user);

    boolean isValidToken(String token);
}
