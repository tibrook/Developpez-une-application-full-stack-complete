package com.openclassrooms.mddapi.service.interfaces;

import com.openclassrooms.mddapi.dto.requests.CreatePostRequest;
import com.openclassrooms.mddapi.dto.PostDto;

public interface PostService {
    PostDto createPost(CreatePostRequest createPostRequest);
    PostDto getPostById(Long id);
}