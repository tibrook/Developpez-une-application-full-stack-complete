package com.openclassrooms.mddapi.dto;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private String content;
    private Long userId;
    private Long postId;

}