package com.energymonitor.controller;

import com.energymonitor.dto.ApiResponse;
import com.energymonitor.dto.auth.LoginDto;
import com.energymonitor.dto.auth.SignUpDto;
import com.energymonitor.dto.auth.TokenDto;
import com.energymonitor.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    // Will handle: User registration, User login, Token refresh, Logout
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

     /*
        * Endpoint for user registration
        * POST /api/auth/signup
     */
     @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<?> registerUser(
             @RequestBody
             @Valid SignUpDto signUpDto) {
         try {
             authService.registerUser(signUpDto);
             return ResponseEntity.ok()
                     .body(new ApiResponse(true, "User registered successfully"));
         } catch (RuntimeException e) {
             return ResponseEntity.badRequest()
                     .body(new ApiResponse(false, e.getMessage()));
         }
     }

     /*
        * Endpoint for user login
        * POST /api/auth/login
     */
     @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
         try {
             TokenDto tokenDto = authService.login(loginDto);
             return ResponseEntity.ok(tokenDto);
         } catch (Exception e) {
             return ResponseEntity.badRequest()
                     .body(new ApiResponse(false, "Invalid credentials"));
         }
     }
}
