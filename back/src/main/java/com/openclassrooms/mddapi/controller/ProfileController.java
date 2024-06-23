package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.requests.UpdateUserRequest;
import com.openclassrooms.mddapi.service.interfaces.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/profile")
@Tag(name = "Users", description = "Endpoints for managing user information")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    
    private UserService userService;

    public UserController(UserService userService) {
    	this.userService = userService;
    }

    @GetMapping("/me")
    @Operation(summary = "Get user info", description = "Fetches the authenticated user's information")
    @ApiResponse(responseCode = "200", description = "User info retrieved successfully",
        content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = UserDto.class)))
    @ApiResponse(responseCode = "404", description = "User not found",
        content = @Content(mediaType = "application/json",
                           examples = @ExampleObject(name = "Not Found", value = "{\"message\":\"User not found\"}")))
    public UserDto getUserInfo(Authentication authentication) {
        log.info("Fetching user info for {}", authentication.getName());
        return userService.getUserById(Long.valueOf(authentication.getName()));
    }

    @Operation(summary = "Update user info", description = "Updates the authenticated user's information")
    @ApiResponse(responseCode = "200", description = "User info updated successfully",
        content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = UserDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
        content = @Content(mediaType = "application/json",
                           examples = @ExampleObject(name = "Validation Failed", value = "{\"message\":\"email : must be a valid email\"}")))
    @PutMapping("/update")
    public UserDto updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        log.info("Update user for user {}", updateUserRequest.getEmail());
        return userService.updateUser(updateUserRequest);
    }
}
