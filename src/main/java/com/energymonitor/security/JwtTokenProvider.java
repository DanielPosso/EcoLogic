package com.energymonitor.security;

/*import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
*/

import com.energymonitor.config.JwtProperties;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

@Component
public class JwtTokenProvider {
    // Will handle: Token generation, Token validation, Token parsing
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    private final JwtProperties jwtProperties;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /*
     * Generates a JWT token for an authenticated user
     * @param authentication The authentication object containing user details
     * @return A JWT token string
    */

    public String generateToken(Authentication authentication) {
        // Cast the principal to UserDetails to get user information
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // Set token creation and expiration dates
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtProperties.getValidityInMs());

        // Build the JWT token with user information and signing key
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // Set user identifier
                .setIssuedAt(new Date())               // Token creation time
                .setExpiration(expiryDate)           // Token expiration time
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey()) //Sign with HS512 algorithm
                .compact(); // Generate the token string

    }

    /*
     * Extracts the username from a JWT token
     * @param token The JWT token string
     * @return The username stored in the token
    */
    public String getUsernameFromToken(String token) {
        // Parse the token and extract the claims (token payload)
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); // Return the username
    }

    /*
     * Validates a JWT token
     * @param token The JWT token string
     * @return true if token is valid, false otherwise
     */
    public boolean validateToken(String token) {
        try {
            // Attempt to parse the token - if successful, token is valid
            Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature"); // Token was modified
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token"); // Token format is wrong
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token"); // Token has expired
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token"); // Token format not supported
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty"); // Token is empty
        }
        return false;
    }

}
