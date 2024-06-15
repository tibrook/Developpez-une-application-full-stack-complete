package com.openclassrooms.mddapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.responses.SubscriptionResponse;
import com.openclassrooms.mddapi.dto.responses.TopicListResponse;
import com.openclassrooms.mddapi.service.interfaces.SubscriptionService;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

//    @GetMapping("/topics/mySubscriptions")
//    public List<TopicListResponse> getUserSubscribedTopics() {
//        return subscriptionService.getUserSubscribedTopics();
//    }
    
    @PostMapping("/{topicId}/subscribe")
    public SubscriptionResponse subscribe(@PathVariable Long topicId) {
        return subscriptionService.subscribe(topicId);
    }

    @DeleteMapping("/{topicId}/unsubscribe")
    public SubscriptionResponse unsubscribe(@PathVariable Long topicId) {
        return subscriptionService.unsubscribe(topicId);
    }
}