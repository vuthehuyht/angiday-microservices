package com.vuthehuyht.userservice.services.impl;

import com.vuthehuyht.userservice.enums.ErrorCode;
import com.vuthehuyht.userservice.exceptions.UsernameExistException;
import com.vuthehuyht.userservice.exceptions.UsernameNotFoundException;
import com.vuthehuyht.userservice.exceptions.UsernameOrPasswordWrongException;
import com.vuthehuyht.userservice.models.Token;
import com.vuthehuyht.userservice.models.User;
import com.vuthehuyht.userservice.repositories.TokenRepository;
import com.vuthehuyht.userservice.repositories.UserRepository;
import com.vuthehuyht.userservice.requests.LoginRequest;
import com.vuthehuyht.userservice.requests.RegisterRequest;
import com.vuthehuyht.userservice.responses.LoginResponse;
import com.vuthehuyht.userservice.responses.RegisterResponse;
import com.vuthehuyht.userservice.services.AuthService;
import com.vuthehuyht.userservice.services.JwtService;
import com.vuthehuyht.userservice.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if (userOptional.isPresent()) {
            log.info("Username {} exist", request.getUsername());
            throw new UsernameExistException(ErrorCode.USERNAME_EXIST);
        }

        User newUser = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .gender(request.getGender())
                .birthday(DateUtil.toDate(request.getBirthday()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userRepository.save(newUser);
        log.info("Create new user with username {}", request.getUsername());
        return new RegisterResponse("Registered new account successfully");
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(ErrorCode.USERNAME_NOTFOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UsernameOrPasswordWrongException(ErrorCode.USERNAME_PASSWORD_WRONG);
        }

        List<Token> tokens = tokenRepository.findAllTokenByUserId(user.getId());
        tokens.forEach(token -> {
            token.setExpired(true);
        });
        tokenRepository.saveAll(tokens);

        var accessToken = jwtService.generateToken(user);

        var token = Token.builder()
                .user(user)
                .token(accessToken)
                .expired(false)
                .build();
        tokenRepository.save(token);
        return new LoginResponse(accessToken);
    }
}
