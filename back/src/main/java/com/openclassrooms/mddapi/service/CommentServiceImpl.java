package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.requests.CreateCommentRequest;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.interfaces.CommentService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing comments on posts.
 * Handles operations such as creating a new comment associated with a post.
 */
@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    
    /**
     * Constructor for dependency injection.
     * @param userRepository Repository for user data access.
     * @param commentRepository Repository for comment data access.
     * @param modelMapper Tool for entity conversion between DTOs and entities.
     * @param postRepository Repository for post data access.
     */
    public CommentServiceImpl(UserRepository userRepository,  CommentRepository commentRepository,ModelMapper modelMapper, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
    }
    
    /**
     * Adds a comment to a post by a logged-in user.
     * @param postId the ID of the post to comment on
     * @param createCommentRequest DTO containing comment content
     * @return CommentDto containing the data of the newly added comment
     * @throws NotFoundException if the user or post does not exist
     */
    @Override
    public CommentDto addComment(Long postId, CreateCommentRequest createCommentRequest) {
	   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.valueOf(authentication.getName());
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new NotFoundException("User not found"));
        String currentUserName = user.getUsername();
        log.info("currentUserName : {}", currentUserName);
        Comment comment = modelMapper.map(createCommentRequest, Comment.class);
        comment.setPost(postRepository.findById(postId).orElseThrow(() -> new NotFoundException("Post not found")));
        comment.setUser(user);
        comment = commentRepository.save(comment);
        return modelMapper.map(comment, CommentDto.class);
    }

}
