package com.openclassrooms.mddapi.service.interfaces;

import com.openclassrooms.mddapi.dto.responses.SubscriptionResponse;

public interface SubscriptionService {
	SubscriptionResponse subscribe(Long topicId);
    SubscriptionResponse unsubscribe(Long topicId);
}
