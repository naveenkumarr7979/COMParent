package com.microservices.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message="Name is required")
    private String firstName;

    @NotBlank(message="Username is required")
    @Size(min=4,max=20)
    private String lastName;

    @Email(message="Invalid Email")
    @NotBlank(message="Email is required")
    private String email;

    @NotBlank(message="Password is required")
    @Size(min=6,message="Password must be minimum 6 characters")
    private String password;

}
