package com.openclassrooms.mddapi.controller;

import jakarta.validation.Valid;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.dto.requests.LoginRequest;
import com.openclassrooms.mddapi.dto.requests.RegisterRequest;
import com.openclassrooms.mddapi.dto.responses.TokenResponse;
import com.openclassrooms.mddapi.exception.AuthenticationException;
import com.openclassrooms.mddapi.service.interfaces.AuthService;
import com.openclassrooms.mddapi.service.interfaces.UserService;

import org.springframework.validation.BindingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;
    private final AuthService authService;
    
    public AuthController(UserService userService,  AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }
    
    @PostMapping("/register")
    public TokenResponse registerUser(@RequestBody @Valid RegisterRequest registerRequest, BindingResult bindingResult) {
        log.info("Registering {}", registerRequest.getEmail());
        if (bindingResult.hasErrors()) {
            String errorDetails = bindingResult.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
            log.error("Register : Validation failed for user {}: {}", registerRequest.getEmail(), errorDetails);
            throw new AuthenticationException("error");
        }
        return authService.registerAndGenerateToken(registerRequest);
    }
     
  
    @PostMapping("/login")
    public TokenResponse loginUser(@RequestBody @Valid  LoginRequest loginRequest, BindingResult bindingResult) {
        log.info("Logging in {}", loginRequest.getEmail());
        if (bindingResult.hasErrors()) {
            String errorDetails = bindingResult.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
            log.error("Login : Validation failed for user {}: {}", loginRequest.getEmail(), errorDetails);
            throw new AuthenticationException("error");
        }
        return authService.authenticateAndGenerateToken(loginRequest);
    }
  
}
