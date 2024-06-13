package com.openclassrooms.mddapi.service.interfaces;

import java.util.List;

import com.openclassrooms.mddapi.dto.responses.TopicListResponse;

public interface TopicService {
    List<TopicListResponse> getAllTopics();
}
