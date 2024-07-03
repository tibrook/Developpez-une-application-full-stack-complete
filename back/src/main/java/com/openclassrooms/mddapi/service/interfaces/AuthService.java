package com.openclassrooms.mddapi.service.interfaces;

import com.openclassrooms.mddapi.dto.requests.LoginRequest;
import com.openclassrooms.mddapi.dto.requests.RegisterRequest;
import com.openclassrooms.mddapi.dto.responses.TokenResponse;

public interface AuthService {
	/**
     * Authenticates the user based on the provided login request and generates a JWT token.
     *
     * @param loginRequest the login request containing the user's credentials (username/email and password)
     * @return a TokenResponse containing the generated JWT token
     * @throws AuthenticationException if the authentication fails
     */
   TokenResponse authenticateAndGenerateToken(LoginRequest loginRequest);
   
   /**
    * Registers a new user based on the provided registration request and generates a JWT token.
    *
    * @param registerRequest the registration request containing the user's email, username, and password
    * @return a TokenResponse containing the generated JWT token
    */
   TokenResponse registerAndGenerateToken(RegisterRequest registerRequest);
}
