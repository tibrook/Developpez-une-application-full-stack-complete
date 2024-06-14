package com.openclassrooms.mddapi.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.responses.SubscriptionResponse;
import com.openclassrooms.mddapi.exception.CustomException;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.service.interfaces.SubscriptionService;
import com.openclassrooms.mddapi.service.interfaces.UserService;

import jakarta.transaction.Transactional;


@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final TopicRepository topicRepository;
    private final SubscriptionRepository subscriptionRepository;
    private static final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);
    private final UserService userService;
    private final ModelMapper modelMapper;
    
    @Autowired
    public SubscriptionServiceImpl(TopicRepository topicRepository,SubscriptionRepository subscriptionRepository, UserService userService, ModelMapper modelMapper) {
        this.topicRepository = topicRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public SubscriptionResponse subscribe( Long topicId) {
        log.info("Subscribe to topic {}", topicId);
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();        
        User user = userService.getUserDetails(currentUserName)
                .map(userDto -> modelMapper.map(userDto, User.class))
                .orElseThrow(() -> new CustomException("User not found"));
        
        Topic topic = topicRepository.findById(topicId)
            .orElseThrow(() -> new CustomException("Topic not found"));
        // Check if subscription already exists
        boolean exists = subscriptionRepository.existsByUserAndTopic(user, topic);
        if (exists) {
            throw new CustomException("Already subscribed to topic ");
        }
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setTopic(topic);
        subscriptionRepository.save(subscription);
        return new SubscriptionResponse("Subscribed successfully to topic " + topicId, topicId);
    }

    @Transactional
    public SubscriptionResponse  unsubscribe( Long topicId) {
        log.info("Unsubscribe to topic {}", topicId);
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();        
        User user = userService.getUserDetails(currentUserName)
                .map(userDto -> modelMapper.map(userDto, User.class))
                .orElseThrow(() -> new CustomException("User not found"));
        
        Topic topic = topicRepository.findById(topicId)
            .orElseThrow(() -> new CustomException("Topic not found"));
        subscriptionRepository.deleteByUserAndTopic(user, topic);
        return new SubscriptionResponse("Unsubscribed successfully from topic " + topicId, topicId);
    }
}
