package com.energymonitor.dto.auth;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class TokenDto {
    private String token;
    private String type = "Bearer";
}
