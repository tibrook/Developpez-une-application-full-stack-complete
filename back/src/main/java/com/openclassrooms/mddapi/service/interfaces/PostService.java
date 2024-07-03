package com.openclassrooms.mddapi.service.interfaces;

import com.openclassrooms.mddapi.dto.requests.CreatePostRequest;

import java.util.List;

import com.openclassrooms.mddapi.dto.PostDto;

public interface PostService {
	
	/**
     * Creates a new post under a specific topic by a logged-in user.
     *
     * @param createPostRequest DTO containing the details of the new post
     * @return PostDto containing the data of the newly created post
     * @throws NotFoundException if the user or topic does not exist
     */
    PostDto createPost(CreatePostRequest createPostRequest);
    
    /**
     * Retrieves a post by its ID, including comments with usernames mapped.
     *
     * @param id the ID of the post to retrieve
     * @return PostDto with details of the post and its comments
     * @throws NotFoundException if the post does not exist
     */
    PostDto getPostById(Long id);
    
    /**
     * Retrieves all posts from topics to which the user is subscribed.
     *
     * @return List of PostDto for all posts in subscribed topics
     * @throws NotFoundException if the user does not exist
     */
    List<PostDto> getPostsBySubscribedTopics();
}