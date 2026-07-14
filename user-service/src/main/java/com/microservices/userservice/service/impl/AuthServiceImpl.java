package com.microservices.userservice.service.impl;

import com.microservices.userservice.dto.LoginRequest;
import com.microservices.userservice.dto.LoginResponse;
import com.microservices.userservice.dto.RegisterRequest;
import com.microservices.userservice.dto.RegisterResponse;
import com.microservices.userservice.entity.User;
import com.microservices.userservice.exception.*;
import com.microservices.userservice.repository.UserRepository;
import com.microservices.userservice.service.AuthService;
import com.microservices.userservice.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (repository.existsByEmail(request.getEmail())) {

            throw new RuntimeException("Email already exists");

        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .build();

        repository.save(user);

        return new RegisterResponse("User Registered Successfully");

    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = repository.findByEmail(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}
