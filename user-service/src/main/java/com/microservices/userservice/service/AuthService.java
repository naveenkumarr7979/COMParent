package com.microservices.userservice.service;

import com.microservices.userservice.dto.LoginRequest;
import com.microservices.userservice.dto.LoginResponse;
import com.microservices.userservice.dto.RegisterRequest;
import com.microservices.userservice.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
