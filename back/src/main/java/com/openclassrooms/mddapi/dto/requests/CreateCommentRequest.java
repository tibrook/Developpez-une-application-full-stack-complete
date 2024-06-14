package com.openclassrooms.mddapi.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCommentRequest {
    @NotEmpty(message = "Title cannot be empty.")
    @Size(max = 255555, message = "Title must not exceed 255555 characters.")
	private String content;
}
