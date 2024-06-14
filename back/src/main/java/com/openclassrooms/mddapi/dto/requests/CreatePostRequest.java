package com.openclassrooms.mddapi.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreatePostRequest {
    
    @NotEmpty(message = "Title cannot be empty.")
    @Size(max = 255, message = "Title must not exceed 255 characters.")
    private String title;
    
    @NotEmpty(message = "Content cannot be empty.")
    private String content;
    
    @NotNull(message = "Topic ID cannot be empty.")
    private Long topicId;
}