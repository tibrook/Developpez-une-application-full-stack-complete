package com.openclassrooms.mddapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Detailed information about a comment")
public class CommentDetailDto {
    @Schema(description = "Username of the person who made the comment", example = "john_doe")
	private String username;
    @Schema(description = "Content of the comment", example = "This is a detailed comment.")
	private String content;
}
