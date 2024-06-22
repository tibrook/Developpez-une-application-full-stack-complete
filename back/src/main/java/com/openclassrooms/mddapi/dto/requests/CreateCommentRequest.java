package com.openclassrooms.mddapi.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCommentRequest {
    @NotEmpty(message = "Content cannot be empty.")
    @Size(max = 5000, message = "Content must not exceed 5000 characters.")
	private String content;
}
