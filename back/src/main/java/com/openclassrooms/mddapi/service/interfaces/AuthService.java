package com.openclassrooms.mddapi.service.interfaces;

import com.openclassrooms.mddapi.dto.requests.LoginRequest;
import com.openclassrooms.mddapi.dto.requests.RegisterRequest;
import com.openclassrooms.mddapi.dto.responses.TokenResponse;

public interface AuthService {
   TokenResponse authenticateAndGenerateToken(LoginRequest loginRequest);
   TokenResponse registerAndGenerateToken(RegisterRequest registerRequest);
}
