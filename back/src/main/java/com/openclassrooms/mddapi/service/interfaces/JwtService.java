package com.openclassrooms.mddapi.service.interfaces;

public interface JwtService {
	String generateTokenWithSubject(String Subject);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
}
