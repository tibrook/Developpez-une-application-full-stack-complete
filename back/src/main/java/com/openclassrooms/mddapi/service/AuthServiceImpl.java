package com.openclassrooms.mddapi.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.openclassrooms.mddapi.dto.requests.LoginRequest;
import com.openclassrooms.mddapi.dto.requests.RegisterRequest;
import com.openclassrooms.mddapi.dto.responses.TokenResponse;
import com.openclassrooms.mddapi.exception.AuthenticationException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.interfaces.AuthService;
import com.openclassrooms.mddapi.service.interfaces.JwtService;
import com.openclassrooms.mddapi.service.interfaces.UserService;

/**
 * Service for managing user authentication and registration.
 * This service leverages Spring Security components for authentication,
 * and generates JWT tokens through JwtService.
 */
@Service
public class AuthServiceImpl implements AuthService{
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    /**
     * Constructor for dependency injection.
     * @param userService the service to manage user operations
     * @param jwtService the service to manage JWT token creation
     * @param authenticationManager the Spring Security authentication manager
     */
    public AuthServiceImpl(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
    	this.userService = userService;
    	this.jwtService=jwtService;
    	this.authenticationManager = authenticationManager;
    }
    
    /**
     * Authenticates the user and generates a JWT token.
     * @param loginRequest the user's login details
     * @return a JWT token wrapped in a TokenResponse
     * @throws AuthenticationException if the user is not found
     */
    public TokenResponse authenticateAndGenerateToken(LoginRequest loginRequest) {
    	log.info("Authenticating user {}", loginRequest.getUsernameOrEmail());

        userService.authenticateUser(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
        User user = userService.findByEmail(loginRequest.getUsernameOrEmail())
                .orElseGet(() -> userService.findByUsername(loginRequest.getUsernameOrEmail())
                        .orElseThrow(() -> new AuthenticationException("User not found")));
        String email = user.getEmail();
        Long userId = user.getId();
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("User {} authenticated successfully", email);
        return new TokenResponse(jwtService.generateTokenWithSubject(String.valueOf(userId)));
    }

    /**
     * Registers a new user and generates a JWT token.
     * @param registerRequest the user's registration data
     * @return a JWT token wrapped in a TokenResponse
     */
    public TokenResponse registerAndGenerateToken(RegisterRequest registerRequest) {
        log.info("Registering user {}", registerRequest.getEmail());
        User newUser = userService.registerUser(registerRequest.getEmail(), registerRequest.getUsername(), registerRequest.getPassword());
        log.info("User {} registered successfully", newUser.getEmail());
        Long userId = newUser.getId();
        return new TokenResponse(jwtService.generateTokenWithSubject(String.valueOf(userId)));
    }
}