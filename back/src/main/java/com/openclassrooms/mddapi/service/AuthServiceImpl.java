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
import com.openclassrooms.mddapi.exception.CustomException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.interfaces.AuthService;
import com.openclassrooms.mddapi.service.interfaces.JwtService;
import com.openclassrooms.mddapi.service.interfaces.UserService;

/**
 * Implementation of AuthService interface providing authentication and registration functionalities.
 */
@Service
public class AuthServiceImpl implements AuthService{
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public AuthServiceImpl(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
    	this.userService = userService;
    	this.jwtService=jwtService;
    	this.authenticationManager = authenticationManager;
    }
 
    public TokenResponse authenticateAndGenerateToken(LoginRequest loginRequest) {
    	log.info("Authenticating user {}", loginRequest.getUsernameOrEmail());

        // Use UserService to authenticate the user first
        userService.authenticateUser(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());

        // Load the user details to get the email
        User user = userService.findByEmail(loginRequest.getUsernameOrEmail())
                .orElseGet(() -> userService.findByUsername(loginRequest.getUsernameOrEmail())
                        .orElseThrow(() -> new CustomException("User not found")));
        String email = user.getEmail();

        // If authentication is successful, proceed with Spring Security Authentication.
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("User {} authenticated successfully", email);
        log.info("Authentication {}", authentication.getName());
        return new TokenResponse(jwtService.generateTokenWithSubject(email));
    }

    public TokenResponse registerAndGenerateToken(RegisterRequest registerRequest) {
        log.info("Registering user {}", registerRequest.getEmail());
        User newUser = userService.registerUser(registerRequest.getEmail(), registerRequest.getName(), registerRequest.getPassword());
        log.info("User {} registered successfully", newUser.getEmail());
        return new TokenResponse(jwtService.generateToken(new UsernamePasswordAuthenticationToken(newUser.getEmail(), null, new ArrayList<>())));
    }
}