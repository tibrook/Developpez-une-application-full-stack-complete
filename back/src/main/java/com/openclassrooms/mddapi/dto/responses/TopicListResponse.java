package com.openclassrooms.mddapi.dto.responses;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Response object for listing topics")
public class TopicListResponse {
    @Schema(description = "ID of the topic", example = "1")
	private Long id;
    @Schema(description = "Name of the topic", example = "Java Development")
    private String name;
    @Schema(description = "Description of the topic", example = "A topic for discussing Java development practices and tips.")
    private String description;
    @Schema(description = "Date and time when the topic was created", example = "2023-06-22T10:00:00")
    private LocalDateTime createdAt;
    @Schema(description = "Subscription status of the current user", example = "true")
    private boolean subscribed; 
}
