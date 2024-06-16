package com.openclassrooms.mddapi.service.interfaces;


import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.requests.CreateCommentRequest;

public interface CommentService {
	CommentDto postComment(Long postId,CreateCommentRequest createCommentRequest);
}