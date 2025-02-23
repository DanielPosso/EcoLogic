package com.energymonitor.service;

import com.energymonitor.dto.auth.LoginDto;
import com.energymonitor.dto.auth.UserRegistrationDto;
import com.energymonitor.dto.auth.TokenDto;
import com.energymonitor.dto.auth.UserRegistrationDto;
import com.energymonitor.entities.User;
import com.energymonitor.repository.UserRepository;
import com.energymonitor.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

     /*
        * Handles user registration
        * @param UserRegistrationDto Registration details
        * @return Newly created user
     */
     @Transactional
     public User registerUser(@Valid UserRegistrationDto userRegistrationDto) {
         // Checks if user already exists
         if (userRepository.existsByEmail(userRegistrationDto.getEmail())) {
             throw new RuntimeException("Email already registered");
         }

         // Create new user
         User user = new User();
         user.setEmail(userRegistrationDto.getEmail());
         user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
         user.setFirstName(userRegistrationDto.getFirstName());
         user.setLastName(userRegistrationDto.getLastName());
         user.setEnabled(true);

         return userRepository.save(user);
     }

     /*
        * Handles user login
        * @param loginDto Login credentials
        * @return JWT token
     */
    public TokenDto login(LoginDto loginDto) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        //Generate token
        String jwt = tokenProvider.generateToken(authentication);

        return new TokenDto(jwt, "Bearer");
    }
}
