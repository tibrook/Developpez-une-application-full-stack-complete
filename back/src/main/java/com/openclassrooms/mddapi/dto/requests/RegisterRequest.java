package com.openclassrooms.mddapi.dto.requests;

import com.openclassrooms.mddapi.validation.ValidPassword;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Schema(description = "Request data for user registration")
public class RegisterRequest {
	
	@Email(message = "Email should be in email format")
	@NotEmpty(message = "Email cannot be empty.")
	@Size(max = 255, message = "Email must not exceed 255 characters.")
    @Schema(description = "User's email address", requiredMode = Schema.RequiredMode.REQUIRED, example = "user@example.com")
    private final String email;
    
    @ValidPassword
	@NotEmpty(message = "Password cannot be empty.")
    @Schema(description = "User's password", requiredMode = Schema.RequiredMode.REQUIRED, example = "Password123#")
    private final String password;
    
	@NotBlank(message = "Username is required and cannot be blank.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username must not contain special characters or spaces.")
    @Size(max = 255, message = "Username must not exceed 255 characters.")
    @Schema(description = "User's username", requiredMode = Schema.RequiredMode.REQUIRED, example = "username")
    private final String username;

}