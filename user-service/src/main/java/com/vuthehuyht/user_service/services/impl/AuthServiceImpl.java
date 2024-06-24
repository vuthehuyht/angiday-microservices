package com.vuthehuyht.user_service.services.impl;

import com.vuthehuyht.user_service.enums.ErrorCode;
import com.vuthehuyht.user_service.exceptions.UsernameExistException;
import com.vuthehuyht.user_service.models.User;
import com.vuthehuyht.user_service.repositories.UserRepository;
import com.vuthehuyht.user_service.requests.RegisterRequest;
import com.vuthehuyht.user_service.responses.RegisterResponse;
import com.vuthehuyht.user_service.services.AuthService;
import com.vuthehuyht.user_service.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

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
}
