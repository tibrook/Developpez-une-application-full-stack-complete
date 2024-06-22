package com.openclassrooms.mddapi.service.interfaces;


import com.openclassrooms.mddapi.dto.requests.CreateCommentRequest;
import com.openclassrooms.mddapi.dto.responses.MessageResponse;

public interface CommentService {
	MessageResponse addComment(Long postId,CreateCommentRequest createCommentRequest);
}