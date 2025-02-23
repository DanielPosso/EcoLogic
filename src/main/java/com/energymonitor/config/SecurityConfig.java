package com.energymonitor.config;

import com.energymonitor.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.Customizer; //Needed to import this in order to fix the private endpoint issue.
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //Marks this as a configuration class
@EnableWebSecurity //Enables Spring Security's web security support
@EnableMethodSecurity //Enables Spring Security's method security support
public class SecurityConfig {

    // Will be implemented in next step
    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection (Cross-Site Request Forgery)
                .csrf(csrf -> csrf.disable())
                // Enable CORS (Cross-Origin Resource Sharing)
                .cors(cors -> cors.configure(http))
                // Use stateless sessions (needed for JWT later)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Configure authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints that don't need authentication
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/test/public").permitAll() // Only public endpoint
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() //Swagger UI
                        .requestMatchers("/api/test-db/**").permitAll() // For testing DB
                        // All other endpoints requests need authentication
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter before UsernamePasswordAuthenticationFilter
                /* //Enable HTTP Basic Authentication
                      httpBasic(Customizer.withDefaults()); // Private endpoint need basic authentication
                */

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
