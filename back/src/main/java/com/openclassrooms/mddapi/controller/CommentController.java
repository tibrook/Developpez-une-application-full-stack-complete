package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.requests.CreateCommentRequest;
import com.openclassrooms.mddapi.service.interfaces.CommentService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public CommentDto addComment(@PathVariable Long postId, @RequestBody @Valid CreateCommentRequest createCommentRequest) {
        return commentService.postComment(postId,createCommentRequest);
    }
}
