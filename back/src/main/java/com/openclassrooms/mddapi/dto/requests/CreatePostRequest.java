package com.openclassrooms.mddapi.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Request data for creating a new post")
public class CreatePostRequest {
    
    @NotEmpty(message = "Title cannot be empty.")
    @Size(max = 255, message = "Title must not exceed 255 characters.")
    @Schema(description = "Title of the post", requiredMode = Schema.RequiredMode.REQUIRED, example = "My First Post")
    private String title;
    
    @Schema(description = "Content of the post", requiredMode = Schema.RequiredMode.REQUIRED, example = "This is the content of my first post")
    @NotEmpty(message = "Content cannot be empty.")
    private String content;
    
    @Schema(description = "Id of the topic", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "Topic ID cannot be empty.")
    private Long topicId;
}