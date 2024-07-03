package com.openclassrooms.mddapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.responses.TopicListResponse;
import com.openclassrooms.mddapi.exception.CustomException;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.interfaces.TopicService;

@Service
public class TopicServiceImpl  implements TopicService{
	 private final TopicRepository topicRepository;
	 private final ModelMapper modelMapper;
	 private final UserRepository userRepository;
	 private final SubscriptionRepository subscriptionRepository;
	 
	@Autowired
	public TopicServiceImpl(TopicRepository topicRepository, SubscriptionRepository subscriptionRepository, UserRepository userRepository, ModelMapper modelMapper) {
	    this.topicRepository = topicRepository;
	    this.subscriptionRepository = subscriptionRepository;
	    this.userRepository = userRepository;
	    this.modelMapper = modelMapper;
	}
	
	/**
     * Retrieves all topics and marks whether the logged-in user is subscribed to each.
     * @return List of TopicListResponse where each response includes subscription status
     * @throws CustomException if the user is not found in the database
     */
    public List<TopicListResponse> getAllTopics() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  	    Long userId = Long.valueOf(authentication.getName());
  	    User user = userRepository.findById(userId)
  	            .orElseThrow(() -> new CustomException("User not found"));
        List<Topic> topics = topicRepository.findAll();
        return topics.stream()
                .map(topic -> {
                    TopicListResponse response = modelMapper.map(topic, TopicListResponse.class);
                    response.setSubscribed(subscriptionRepository.existsByUserAndTopic(user, topic));
                    return response;
                })
                .collect(Collectors.toList());
    }
}
