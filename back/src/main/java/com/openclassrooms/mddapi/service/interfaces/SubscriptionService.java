package com.openclassrooms.mddapi.service.interfaces;

import java.util.List;

import com.openclassrooms.mddapi.dto.responses.SubscriptionResponse;
import com.openclassrooms.mddapi.dto.responses.TopicListResponse;

public interface SubscriptionService {
	SubscriptionResponse subscribe(Long topicId);
    SubscriptionResponse unsubscribe(Long topicId);
    List<TopicListResponse> getUserSubscribedTopics();
}
