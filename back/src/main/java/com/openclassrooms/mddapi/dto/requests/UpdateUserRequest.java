package com.openclassrooms.mddapi.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateUserRequest {
	
	@NotBlank(message = "Name is required and cannot be blank.")
    @Size(max = 255, message = "Name must not exceed 255 characters.")
    private String username;
	
    @Email(message = "Email should be in email format")
	@NotEmpty(message = "Email cannot be empty.")
	@Size(max = 255, message = "Email must not exceed 255 characters.")
    private String email;

}
