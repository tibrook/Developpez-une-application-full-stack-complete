package com.openclassrooms.mddapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for a comment")
public class CommentDto {
    @Schema(description = "ID of the comment", example = "1")
    private Long id;
    @Schema(description = "Content of the comment", example = "This is a comment.")
    private String content;
    @Schema(description = "ID of the user who made the comment", example = "42")
    private Long userId;
    @Schema(description = "ID of the post the comment is associated with", example = "101")
    private Long postId;

}