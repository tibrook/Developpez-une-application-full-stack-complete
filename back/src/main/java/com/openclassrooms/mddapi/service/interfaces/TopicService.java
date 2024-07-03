package com.openclassrooms.mddapi.service.interfaces;

import java.util.List;

import com.openclassrooms.mddapi.dto.responses.TopicListResponse;

public interface TopicService {
	
	/**
     * Retrieves all topics and marks whether the logged-in user is subscribed to each.
     *
     * @return a list of TopicListResponse where each response includes subscription status
     * @throws CustomException if the user is not found in the database
     */
    List<TopicListResponse> getAllTopics();
}
