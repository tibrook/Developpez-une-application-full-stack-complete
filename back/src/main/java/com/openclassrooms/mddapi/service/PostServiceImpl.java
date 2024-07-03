package com.openclassrooms.mddapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.requests.CreatePostRequest;
import com.openclassrooms.mddapi.dto.CommentDetailDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.interfaces.PostService;
import com.openclassrooms.mddapi.exception.NotFoundException;


@Service
public class PostServiceImpl implements PostService {
    private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    private final PostRepository postRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final TopicRepository topicRepository;
    private final ModelMapper modelMapper;

    public PostServiceImpl(UserRepository userRepository, CommentRepository commentRepository, TopicRepository topicRepository, ModelMapper modelMapper, SubscriptionRepository subscriptionRepository,PostRepository postRepository ) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.topicRepository = topicRepository;
        this.modelMapper = modelMapper;
        this.subscriptionRepository = subscriptionRepository;
        this.postRepository=postRepository;
    }

    /**
     * Creates a new post by a logged-in user under a specific topic.
     * @param createPostRequest DTO containing the new post's details
     * @return PostDto containing the data of the newly created post
     * @throws NotFoundException if the user or topic does not exist
     */
    @Override
    public PostDto createPost(CreatePostRequest createPostRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    Long userId = Long.valueOf(authentication.getName());
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new NotFoundException("User not found"));
	    
        String currentUserName = user.getUsername();
        log.info("currentUserName : {}", currentUserName);
    
        Topic topic = topicRepository.findById(createPostRequest.getTopicId())
                .orElseThrow(() -> new NotFoundException("Topic not found"));
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

    /**
     * Retrieves a post by its ID, including comments with usernames mapped.
     * @param id the ID of the post to retrieve
     * @return PostDto with details of the post and its comments
     * @throws NotFoundException if the post does not exist
     */
    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found"));
        PostDto postDto = modelMapper.map(post, PostDto.class);
        postDto.setAuthor(post.getUser().getUsername());
        postDto.setTopicName(post.getTopic().getName());

        List<Comment> comments = commentRepository.findByPostId(id);
        if (!comments.isEmpty()) {
            List<Long> userIds = comments.stream()
                .map(comment -> comment.getUser().getId())
                .distinct()
                .collect(Collectors.toList());

            List<User> users = userRepository.findAllById(userIds);

            Map<Long, String> usernameMap = users.stream()
                .collect(Collectors.toMap(User::getId, User::getUsername));

            List<CommentDetailDto> commentDtos = comments.stream()
                .map(comment -> {
                    CommentDetailDto dto = modelMapper.map(comment, CommentDetailDto.class);
                    dto.setUsername(usernameMap.get(comment.getUser().getId())); 
                    return dto;
                })
                .collect(Collectors.toList());

            postDto.setComments(commentDtos);
        } else {
            postDto.setComments(new ArrayList<>());
        }

        return postDto;
    }
    
    /**
     * Retrieves all posts from topics to which the user is subscribed.
     * @return List of PostDto for all posts in subscribed topics
     * @throws NotFoundException if the user does not exist
     */
    @Override
    public List<PostDto> getPostsBySubscribedTopics() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Long userId = Long.valueOf(authentication.getName());
  	    User user = userRepository.findById(userId)
  	            .orElseThrow(() -> new NotFoundException("User not found"));

  	    List<Subscription> subscriptions = subscriptionRepository.findByUser(user);

  	    List<Post> posts = subscriptions.stream()
              .flatMap(subscription -> postRepository.findByTopic(subscription.getTopic()).stream())
              .collect(Collectors.toList());

  	    return posts.stream()
              .map(post -> {
                  PostDto postDto = modelMapper.map(post, PostDto.class);
                  postDto.setAuthor(post.getUser().getUsername());
                  return postDto;
              })
              .collect(Collectors.toList());
    }
}
