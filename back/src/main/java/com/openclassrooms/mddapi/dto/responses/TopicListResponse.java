package com.openclassrooms.mddapi.dto.responses;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TopicListResponse {
	private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
