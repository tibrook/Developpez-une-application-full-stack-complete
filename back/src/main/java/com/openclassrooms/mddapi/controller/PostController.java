package com.openclassrooms.mddapi.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.openclassrooms.mddapi.dto.requests.CreatePostRequest;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.service.interfaces.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @PostMapping
    public PostDto createPost(@RequestBody @Valid CreatePostRequest createPostRequest) {
    	return postService.createPost(createPostRequest);
    }
 
    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable Long id) {
    	return postService.getPostById(id);
    }
}
