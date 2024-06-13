package com.openclassrooms.mddapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.responses.TopicListResponse;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.service.interfaces.TopicService;

@Service
public class TopicServiceImpl  implements TopicService{
	 private final TopicRepository topicRepository;
	 private final ModelMapper modelMapper;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, ModelMapper modelMapper) {
        this.topicRepository = topicRepository;
        this.modelMapper = modelMapper;
    }

    public List<TopicListResponse> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();
        return topics.stream()
                     .map(topic -> modelMapper.map(topic, TopicListResponse.class))
                     .collect(Collectors.toList());
    }
}
