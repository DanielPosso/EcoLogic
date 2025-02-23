package com.energymonitor.dto.auth;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/*
//First base version
@Data
public class LoginDto {
    private String email;
    private String password;
}*/

@Data
public class LoginDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
