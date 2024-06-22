package com.openclassrooms.mddapi.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Request data to create a new comment")
public class CreateCommentRequest {
    @NotEmpty(message = "Content cannot be empty.")
    @Size(max = 5000, message = "Content must not exceed 5000 characters.")
    @Schema(description = "Content of the comment", requiredMode = Schema.RequiredMode.REQUIRED, example = "This is a comment.")
	private String content;
}
