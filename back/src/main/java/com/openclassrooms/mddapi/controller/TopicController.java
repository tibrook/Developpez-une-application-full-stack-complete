package com.openclassrooms.mddapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.responses.SubscriptionResponse;
import com.openclassrooms.mddapi.dto.responses.TopicListResponse;
import com.openclassrooms.mddapi.service.interfaces.SubscriptionService;
import com.openclassrooms.mddapi.service.interfaces.TopicService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/auth/topics")
@Tag(name = "Topics", description = "Endpoints for managing topics and subscriptions")
public class TopicController {
    private static final Logger log = LoggerFactory.getLogger(TopicController.class);

    private final TopicService topicService;
    private final SubscriptionService subscriptionService;
    
    public TopicController(TopicService topicService, SubscriptionService subscriptionService) {
        this.topicService = topicService;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    @Operation(summary = "Get all topics", description = "Fetches a list of all available topics")
    @ApiResponse(responseCode = "200", description = "List of topics retrieved successfully",
        content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = TopicListResponse.class)))
    public List<TopicListResponse> getAllTopics() {
        log.info("Get All Topics");
        return topicService.getAllTopics();
    }
    
    @Operation(summary = "Subscribe to a topic", description = "Subscribes the authenticated user to the specified topic")
    @ApiResponse(responseCode = "200", description = "Subscription successful",
        content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = SubscriptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Topic not found",
        content = @Content(mediaType = "application/json",
                           examples = @ExampleObject(name = "Not Found", value = "{\"message\":\"Topic with ID {topicId} not found\"}")))
    @PostMapping("/{topicId}/subscribe")
    public SubscriptionResponse subscribe(@PathVariable Long topicId) {
        log.info("Subscribe to topic {}", topicId);
        return subscriptionService.subscribe(topicId);
    }

    @Operation(summary = "Unsubscribe from a topic", description = "Unsubscribes the authenticated user from the specified topic")
    @ApiResponse(responseCode = "200", description = "Unsubscription successful",
        content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = SubscriptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Topic not found",
        content = @Content(mediaType = "application/json",
                           examples = @ExampleObject(name = "Not Found", value = "{\"message\":\"Topic with ID {topicId} not found\"}")))
    @DeleteMapping("/{topicId}/unsubscribe")
    public SubscriptionResponse unsubscribe(@PathVariable Long topicId) {
        log.info("Unsubscribe to topic {}", topicId);
        return subscriptionService.unsubscribe(topicId);
    }
}