package com.openclassrooms.mddapi.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequest {
		
	@NotEmpty(message = "Username or Email cannot be empty.")
    private final String usernameOrEmail;
    
	@NotEmpty(message = "Password cannot be empty.")
	@Size(min = 8, message = "Password must be at least 8 characters long.")
    private final String password;
    
    public LoginRequest(String usernameOrEmail , String password) {
        this.usernameOrEmail  = usernameOrEmail ;
        this.password = password;
    }
}

