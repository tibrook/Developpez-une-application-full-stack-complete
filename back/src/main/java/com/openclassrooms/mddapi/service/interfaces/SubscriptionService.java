package com.openclassrooms.mddapi.service.interfaces;

import com.openclassrooms.mddapi.dto.responses.SubscriptionResponse;

public interface SubscriptionService {
	
	/**
     * Subscribes a user to a specific topic.
     *
     * @param topicId the ID of the topic to subscribe to
     * @return SubscriptionResponse indicating the result of the subscription operation
     * @throws NotFoundException if the user or topic is not found
     * @throws BadRequestException if the user is already subscribed to the topic
     */
	SubscriptionResponse subscribe(Long topicId);
	
	 /**
     * Unsubscribes a user from a specific topic.
     *
     * @param topicId the ID of the topic to unsubscribe from
     * @return SubscriptionResponse indicating the result of the unsubscription operation
     * @throws NotFoundException if the user or topic is not found
     * @throws BadRequestException if the user is not subscribed to the topic
     */
    SubscriptionResponse unsubscribe(Long topicId);
}
