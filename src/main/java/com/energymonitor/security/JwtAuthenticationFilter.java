package com.energymonitor.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//Second Step
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
//
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
 * Filter that intercepts every request to check for JWT token
 * and authenticate users based on the token
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider, UserDetailsService userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }

    // Will handle: JWT token extraction from requests, Token validation, Setting authentication in context
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // Extract JWT token from the request header
            String jwt = getJwtFromRequest(request);

            // If token exists and is valid, authenticate the user
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                // Get username from token
                String username = tokenProvider.getUsernameFromToken(jwt);
                // Load user details from database
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // Create authentication token
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, // Principal (user details)
                                null, // Credentials (not needed after authentication)
                                userDetails.getAuthorities() // User authorities/roles
                        );
                // Add request details to authentication token
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Set authentication in Spring Security context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }
        // Continue with the request chain
        filterChain.doFilter(request, response);
    }

    /*
     * Extracts JWT token from request Authorization header
     * Format: "Bearer <token>"
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


}