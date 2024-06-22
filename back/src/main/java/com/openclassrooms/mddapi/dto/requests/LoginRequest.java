package com.openclassrooms.mddapi.dto.requests;

import com.openclassrooms.mddapi.validation.ValidPassword;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
@Schema(description = "Request data for user login")
public class LoginRequest {
		
	@NotEmpty(message = "Username or Email cannot be empty.")
    @Schema(description = "User's username or email address", requiredMode = Schema.RequiredMode.REQUIRED, example = "user@example.com")
    private final String usernameOrEmail;
    
	@NotEmpty(message = "Password cannot be empty.")
	@ValidPassword
    @Schema(description = "User's password", requiredMode = Schema.RequiredMode.REQUIRED, example = "Password123#")
    private final String password;
    
    public LoginRequest(String usernameOrEmail , String password) {
        this.usernameOrEmail  = usernameOrEmail ;
        this.password = password;
    }
}

