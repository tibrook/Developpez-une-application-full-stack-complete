package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.requests.CreateCommentRequest;
import com.openclassrooms.mddapi.exception.CustomException;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.service.interfaces.CommentService;
import com.openclassrooms.mddapi.service.interfaces.UserService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private final UserService userService;
    
    public CommentServiceImpl(UserService userService) {
        this.userService = userService;
    }
    @Override
    public CommentDto postComment(Long postId, CreateCommentRequest createCommentRequest) {
	   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String currentUserName = authentication.getName();
       log.info("currentUserName : {}", currentUserName);
       User user = userService.getUserDetails(currentUserName)
               .map(userDto -> modelMapper.map(userDto, User.class))
               .orElseThrow(() -> new CustomException("User not found"));
           
        Comment comment = modelMapper.map(createCommentRequest, Comment.class);
        comment.setPost(postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found")));
        comment.setUser(user);
        comment = commentRepository.save(comment);
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }
}
