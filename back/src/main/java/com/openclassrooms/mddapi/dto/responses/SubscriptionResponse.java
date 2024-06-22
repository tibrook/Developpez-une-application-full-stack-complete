package com.openclassrooms.mddapi.dto.responses;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Response object for subscription actions")
public class SubscriptionResponse {
    @Schema(description = "Detailed message about the subscription action", example = "Successfully subscribed to the topic")
	private String message;
    @Schema(description = "ID of the topic", example = "123")
    private Long topicId;
}

