// DTO for user registration - Transfers user registration data between client and server
// Separates entity model from API layer
package com.energymonitor.dto.auth;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
