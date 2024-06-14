package com.openclassrooms.mddapi.service.interfaces;

import org.springframework.security.core.Authentication;

public interface JwtService {
	String generateToken(Authentication authentication);
	String generateTokenWithSubject(String Subject);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
}
