package com.openclassrooms.mddapi.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String topicName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentDetailDto> comments;
}