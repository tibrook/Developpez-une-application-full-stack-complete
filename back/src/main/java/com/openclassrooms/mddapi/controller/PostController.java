package com.openclassrooms.mddapi.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import com.openclassrooms.mddapi.dto.requests.CreatePostRequest;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.service.interfaces.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/auth/posts")
@Tag(name = "Posts", description = "Endpoints for managing posts")
public class PostController {
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;
    
    @PostMapping("/add")
    @Operation(summary = "Create a new post", description = "Creates a new post and returns the created post")
    @ApiResponse(responseCode = "201", description = "Post created successfully",
        content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = PostDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
        content = @Content(mediaType = "application/json",
                           examples = @ExampleObject(name = "Validation Failed", value = "{\"message\":\"title: Title cannot be empty.\"}")))
    @ApiResponse(responseCode = "404", description = "Not Found",
    content = @Content(mediaType = "application/json",
                       examples = @ExampleObject(name = "Topic Not Found", value = "{\"message\":\"Topic Not Found.\"}")))
    public PostDto createPost(@RequestBody @Valid CreatePostRequest createPostRequest, BindingResult bindingResult) {
        log.info("Create Post {}", createPostRequest.getTitle());
        if (bindingResult.hasErrors()) {
            String errorDetails = bindingResult.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
            log.error("createPost : Validation failed : {}",  errorDetails);
            throw new BadRequestException(errorDetails);
        }
    	return postService.createPost(createPostRequest);
    }
 
    @Operation(summary = "Get a post by ID", description = "Fetches a post by its ID")
    @ApiResponse(responseCode = "200", description = "Post found",
        content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = PostDto.class)))
    @ApiResponse(responseCode = "404", description = "Post not found",
        content = @Content(mediaType = "application/json",
                           examples = @ExampleObject(name = "Not Found", value = "{\"message\":\"Post Not Found.\"}")))
    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable Long id) {
        log.info("Get Post By Id: {}", id);
    	return postService.getPostById(id);
    }
    
    @Operation(summary = "Get posts by subscribed topics", description = "Fetches posts from topics the user is subscribed to")
    @ApiResponse(responseCode = "200", description = "Posts retrieved successfully",
        content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = PostDto.class)))
    @GetMapping("/feed")
    public List<PostDto> getSubscribedTopicsPosts() {
        log.info("Get All Posts in the feed");
        return postService.getPostsBySubscribedTopics();
    }
}
