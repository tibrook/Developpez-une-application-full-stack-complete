package com.openclassrooms.mddapi.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubscriptionResponse {
	private String message;
    private Long topicId;
}

