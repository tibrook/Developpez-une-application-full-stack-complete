package com.openclassrooms.mddapi.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Response data containing the authentication token")
public class TokenResponse {
    @Schema(description = "JWT authentication token", requiredMode = Schema.RequiredMode.REQUIRED, example = "eyJhbGciOiJIUzI1NiIsInR...")
    private final String token;
}
