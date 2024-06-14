package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.requests.UpdateUserRequest;
import com.openclassrooms.mddapi.service.interfaces.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public UserDto getUserInfo(Authentication authentication) {
        log.info("Fetching user info for {}", authentication.getName());
        return userService.getUserById(Long.valueOf(authentication.getName()));
    }

    @PutMapping("/me")
    public UserDto updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }
}
