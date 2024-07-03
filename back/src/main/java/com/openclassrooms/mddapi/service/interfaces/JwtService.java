package com.openclassrooms.mddapi.service.interfaces;

public interface JwtService {
	 /**
     * Generates a JWT token for the provided subject.
     *
     * @param subject the subject (typically user ID or email) for which the token is generated
     * @return the generated JWT token as a string
     */
	String generateTokenWithSubject(String Subject);
	
	/**
     * Validates the provided JWT token.
     *
     * @param token the JWT token to be validated
     * @return true if the token is valid, false otherwise
     * @throws JwtAuthenticationException if the token is malformed or expired
     */
    boolean validateToken(String token);
    
    /**
     * Extracts the username (email) from the provided JWT token.
     *
     * @param token the JWT token from which to extract the username
     * @return the username (email) extracted from the token
     * @throws UsernameNotFoundException if the user is not found
     */
    String getUsernameFromToken(String token);
}
