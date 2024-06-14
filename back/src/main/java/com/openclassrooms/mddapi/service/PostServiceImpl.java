package com.openclassrooms.mddapi.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.requests.CreatePostRequest;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.interfaces.PostService;
import com.openclassrooms.mddapi.service.interfaces.UserService;
import com.openclassrooms.mddapi.exception.CustomException;


@Service
public class PostServiceImpl implements PostService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private PostRepository postRepository;
    
    private final UserRepository userRepository;


    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PostServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public PostDto createPost(CreatePostRequest createPostRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    Long userId = Long.valueOf(authentication.getName());
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new CustomException("User not found"));
	    
        String currentUserName = user.getUsername();
        log.info("currentUserName : {}", currentUserName);
    
        Topic topic = topicRepository.findById(createPostRequest.getTopicId())
                .orElseThrow(() -> new CustomException("Topic not found"));
        Post post = new Post();
        post.setUser(user);
        post.setTopic(topic);
        post.setTitle(createPostRequest.getTitle());
        post.setContent(createPostRequest.getContent());

        Post savedPost = postRepository.save(post);
        PostDto postDto = modelMapper.map(savedPost, PostDto.class);
        log.info("user : {}", user.getUsername());

        postDto.setAuthor(user.getUsername());
        return postDto;
    }


    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException("Post not found"));
        return modelMapper.map(post, PostDto.class);
    }
}
