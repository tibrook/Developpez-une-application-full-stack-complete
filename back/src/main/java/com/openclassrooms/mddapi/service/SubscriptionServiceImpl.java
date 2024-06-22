package com.openclassrooms.mddapi.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.responses.SubscriptionResponse;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.CustomException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.interfaces.SubscriptionService;
import jakarta.transaction.Transactional;


@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final TopicRepository topicRepository;
    private final SubscriptionRepository subscriptionRepository;
    private static final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);
    private final UserRepository userRepository;
    @Autowired
    public SubscriptionServiceImpl(TopicRepository topicRepository,SubscriptionRepository subscriptionRepository,  UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public SubscriptionResponse subscribe( Long topicId) {
        log.info("Subscribe to topic {}", topicId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Long userId = Long.valueOf(authentication.getName());
        User user = userRepository.findById(userId)
	            .orElseThrow(() -> new NotFoundException("User not found"));
        
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new NotFoundException("Topic with "+ topicId +" not found"));
        // Check if subscription already exists
        boolean exists = subscriptionRepository.existsByUserAndTopic(user, topic);
        if (exists) {
            throw new BadRequestException("Already subscribed to topic ");
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
    	Long userId = Long.valueOf(authentication.getName());
        User user = userRepository.findById(userId)
	            .orElseThrow(() -> new NotFoundException("User not found"));
        
        Topic topic = topicRepository.findById(topicId)
            .orElseThrow(() -> new NotFoundException("Topic with "+ topicId +" not found"));
        
        // Verify if the subscription exists
        Optional<Subscription> subscriptionOpt = subscriptionRepository.findByUserAndTopic(user, topic);
        if (!subscriptionOpt.isPresent()) {
            throw new BadRequestException("No subscription found to unsubscribe");
        }
        
        subscriptionRepository.deleteByUserAndTopic(user, topic);
        return new SubscriptionResponse("Unsubscribed successfully from topic " + topicId, topicId);
    }

}
