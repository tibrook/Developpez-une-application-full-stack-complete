package com.openclassrooms.mddapi.service.interfaces;


import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.requests.CreateCommentRequest;

public interface CommentService {
	
	/**
     * Adds a comment to a specific post.
     *
     * @param postId the ID of the post to which the comment will be added
     * @param createCommentRequest the request containing the comment content
     * @return a CommentDto containing the details of the newly added comment
     * @throws NotFoundException if the post or user is not found
     */
	CommentDto addComment(Long postId,CreateCommentRequest createCommentRequest);
}