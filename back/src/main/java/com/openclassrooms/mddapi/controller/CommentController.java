package com.openclassrooms.mddapi.controller;
import com.openclassrooms.mddapi.dto.requests.CreateCommentRequest;
import com.openclassrooms.mddapi.dto.responses.MessageResponse;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.service.interfaces.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/posts/{postId}/comment")
@Tag(name = "Comments", description = "Endpoints for managing comments on posts")
public class CommentController {
    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    private CommentService commentService;
    
    public CommentController( CommentService commentService) {
        this.commentService = commentService;
    }
    
    @PostMapping
    @Operation(summary = "Add a new comment to a post", description = "Adds a new comment to the specified post and returns the created comment")
    @ApiResponse(responseCode = "200", description = "Comment added successfully",
        content = @Content(mediaType = "application/json",
        		 schema = @Schema(implementation = MessageResponse.class),  examples = @ExampleObject(value = "{\"message\": \"Comment Added Successfully\"}")))
    @ApiResponse(responseCode = "400", description = "Bad Request",
        content = @Content(mediaType = "application/json",
                           examples = @ExampleObject(name = "Validation Failed", value = "{\"message\":\"content: Content cannot be empty.\"}")))
    @ApiResponse(responseCode = "404", description = "Not Found",
    content = @Content(mediaType = "application/json",
                       examples = @ExampleObject(name = "Post Not Found", value = "{\"message\":\"Post Not Found\"}")))
    public MessageResponse addComment(@PathVariable Long postId, @RequestBody @Valid CreateCommentRequest createCommentRequest, BindingResult bindingResult) {
        log.info("Add Comment {}", createCommentRequest.getContent());
        if (bindingResult.hasErrors()) {
            String errorDetails = bindingResult.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
            log.error("addComment : Validation failed for postId {}: {}", postId, errorDetails);
            throw new BadRequestException(errorDetails);
        }
    	return commentService.addComment(postId,createCommentRequest);
    }
}
