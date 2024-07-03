package com.openclassrooms.mddapi.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Response object for error messages")
public class ErrorResponse {
    @Schema(description = "Detailed error message", example = "An unexpected error occurred")
    private String message;
}
