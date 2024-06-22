package com.openclassrooms.mddapi.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Data transfer object for a post")
public class PostDto {
    @Schema(description = "ID of the post", example = "1")
    private Long id;
    @Schema(description = "Title of the post", example = "My First Post")
    private String title;
    @Schema(description = "Content of the post", example = "This is the content of my first post")
    private String content;
    @Schema(description = "Author of the post", example = "John Doe")
    private String author;
    private String topicName;
    @Schema(description = "Creation date of the post", example = "2023-06-22T10:00:00Z")
    private LocalDateTime createdAt;
    @Schema(description = "Creation date of the post", example = "2023-06-22T10:00:00Z")
    private LocalDateTime updatedAt;
    private List<CommentDetailDto> comments;
}