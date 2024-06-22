package com.openclassrooms.mddapi.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Schema(description = "Response object for generic messages")
public class MessageResponse {
    @Schema(description = "Detailed message", example = "Operation completed successfully")
    private String message;
}

