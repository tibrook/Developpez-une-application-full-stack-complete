package com.openclassrooms.mddapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for user information")
public class UserDto {
    @Schema(description = "ID of the user", example = "1")
	private Long id;
    @Schema(description = "Email of the user", example = "user@example.com")
    private String email;
    @Schema(description = "Username of the user", example = "username")
    private String username;
}