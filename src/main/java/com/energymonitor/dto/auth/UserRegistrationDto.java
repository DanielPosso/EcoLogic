// DTO for user registration - Transfers user registration data between client and server
// Separates entity model from API layer
package com.energymonitor.dto.auth;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class UserRegistrationDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;
}
