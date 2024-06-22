package com.openclassrooms.mddapi.controller;

import jakarta.validation.Valid;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.dto.requests.LoginRequest;
import com.openclassrooms.mddapi.dto.requests.RegisterRequest;
import com.openclassrooms.mddapi.dto.responses.TokenResponse;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.service.interfaces.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.validation.BindingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;
    
    public AuthController( AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Registers a new user and returns a JWT token")
    @ApiResponse(responseCode = "200", description = "Successful registration",
        content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = TokenResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
    content = @Content(mediaType = "application/json",
                       examples = @ExampleObject(name = "Validation Failed", value = "{\"message\":\"username: Username is required and cannot be blank.\"}")))    
    @ApiResponse(responseCode = "409", description = "Conflict",
    content = @Content(mediaType = "application/json",
                       examples = @ExampleObject(name = "Email Already Exists", value = "{\"message\":\"Email already exists\"}")))    
    public TokenResponse registerUser(@RequestBody @Valid RegisterRequest registerRequest, BindingResult bindingResult) {
        log.info("Registering {}", registerRequest.getEmail());
        if (bindingResult.hasErrors()) {
            String errorDetails = bindingResult.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
            log.error("Register : Validation failed for user {}: {}", registerRequest.getEmail(), errorDetails);
            throw new BadRequestException(errorDetails);
        }
        return authService.registerAndGenerateToken(registerRequest);
    }
    
    @Operation(summary = "Login an existing user", description = "Authenticates a user and returns a JWT token")
    @ApiResponse(responseCode = "200", description = "Successful login",
        content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = TokenResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
        content = @Content(mediaType = "application/json",
                           examples = @ExampleObject(name = "Validation Failed", value = "{\"message\":\"password: Password cannot be empty., password: Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character.\"}")))
    @ApiResponse(responseCode = "401", description = "Unauthorized",
        content = @Content(mediaType = "application/json",
                           examples = @ExampleObject(name = "Invalid Credentials", value = "{\"message\":\"Invalid credentials\"}")))
    @PostMapping("/login")
    public TokenResponse loginUser(@RequestBody @Valid LoginRequest loginRequest, BindingResult bindingResult) {
        log.info("Logging in {}", loginRequest.getUsernameOrEmail());
        if (bindingResult.hasErrors()) {
            String errorDetails = bindingResult.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
            log.error("Login : Validation failed for user {}: {}", loginRequest.getUsernameOrEmail(), errorDetails);
            throw new BadRequestException(errorDetails);
        }
        return authService.authenticateAndGenerateToken(loginRequest);
    }
  
}
