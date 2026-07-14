package com.microservices.userservice.controller;

import com.microservices.userservice.dto.LoginRequest;
import com.microservices.userservice.dto.LoginResponse;
import com.microservices.userservice.dto.RegisterRequest;
import com.microservices.userservice.dto.RegisterResponse;
import com.microservices.userservice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private  final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request)
    {
        System.out.println("login api hit");
        return authService.login(request);
    }

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {

        return authService.register(request);

    }
}
