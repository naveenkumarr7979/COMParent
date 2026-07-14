package com.microservices.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginRequest {
    @NotBlank(message="Username is required")
    private String username;

    @NotBlank(message="Password is required")
    private String password;
}
