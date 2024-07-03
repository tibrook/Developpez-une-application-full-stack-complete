package com.openclassrooms.mddapi.dto.requests;

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
@Schema(description = "Request data to update user information")
public class UpdateUserRequest {
	
	@NotBlank(message = "Username is required and cannot be blank.")
    @Size(max = 255, message = "Username must not exceed 255 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username must not contain special characters or spaces.")
    @Schema(description = "New username of the user", example = "newusername")
    private String username;
	
    @Email(message = "Email should be in email format")
	@NotEmpty(message = "Email cannot be empty.")
	@Size(max = 255, message = "Email must not exceed 255 characters.")
    @Schema(description = "New email of the user", example = "newuser@example.com")
    private String email;

}
