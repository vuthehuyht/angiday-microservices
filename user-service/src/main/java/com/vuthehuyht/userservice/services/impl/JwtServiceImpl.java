package com.vuthehuyht.userservice.services.impl;

import com.vuthehuyht.userservice.enums.ErrorCode;
import com.vuthehuyht.userservice.exceptions.*;
import com.vuthehuyht.userservice.models.User;
import com.vuthehuyht.userservice.repositories.UserRepository;
import com.vuthehuyht.userservice.services.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.expiration}") private Long expiration;
    @Value("${jwt.secret}") private String secret;

    private final UserRepository userRepository;

    @Override
    public Claims extractClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public Key getKey() {
        byte[] keys = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keys);
    }

    @Override
    public String generateToken(User user) {
        Instant currentDateTime = Instant.now();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(Date.from(currentDateTime))
                .setExpiration(Date.from(currentDateTime.plusSeconds(expiration)))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isValidToken(String token) {
        final String username = extractUsernameByToken(token);
        User userDetails = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorCode.USERNAME_NOTFOUND));

        return userDetails != null;
    }

    private String extractUsernameByToken(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractClaimsByToken(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractClaimsByToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (UnsupportedJwtException e) {
            log.error("Unsupport JWT token {}", e.getLocalizedMessage());
            throw new TokenNotSupportException(ErrorCode.TOKEN_NOT_SUPPORT);
        } catch (SignatureException | MalformedJwtException e) {
            log.error("Token is invalid format {}", e.getLocalizedMessage());
            throw new TokenInvalidException(ErrorCode.TOKEN_INVALID);
        } catch (ExpiredJwtException e) {
            log.error("Token is expired {}", e.getLocalizedMessage());
            throw new TokenExpiredException(ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            log.error("{}", e.getLocalizedMessage());
            throw new UnknownErrorException(ErrorCode.UNKNOWN_ERROR);
        }
    }
}
