package com.openclassrooms.mddapi.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.exception.JwtAuthenticationException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.interfaces.JwtService;


/**
 * Implementation of JwtService interface providing JWT token generation, validation, and extraction functionalities.
 */
@Service
public class JwtServiceImpl implements JwtService{

	private JwtEncoder jwtEncoder;
	private JwtDecoder jwtDecoder;
	private UserRepository userRepository;
	@Value("${jwt.expiration}")
	private long jwtExpiration; 
	
	/**
	 * Constructs a JwtServiceImpl instance.
	 * @param jwtEncoder JwtEncoder instance for encoding JWT tokens.
	 * @param jwtDecoder JwtDecoder instance for decoding JWT tokens.
	 */
	public JwtServiceImpl(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, UserRepository userRepository) {
		this.jwtEncoder = jwtEncoder;
		this.jwtDecoder = jwtDecoder;
		this.userRepository = userRepository;
	}
	/**
	 * Generates a JWT token for the provided subject (email).
	 * @param subject user Id of the authenticated user.
	 * @return JWT token generated for the subject.
	 */
	public String generateTokenWithSubject(String userId) {
    	Instant now = Instant.now();
 		JwtClaimsSet claims = JwtClaimsSet.builder()
          		  .issuer("self")
           		  .issuedAt(now)
           		  .expiresAt(now.plus(jwtExpiration, ChronoUnit.SECONDS))
          		  .subject(userId)
          		  .build();
		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
		return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
	}
	
	/**
	 * Validates the provided JWT token.
	 * @param token JWT token to be validated.
	 * @return true if the token is valid, false otherwise.
	 * @throws JwtAuthenticationException if the token is malformed or expired.
	 */
	public boolean validateToken(String token) {
	    try {
	        Jwt jwt = jwtDecoder.decode(token);
	        if (jwt.getExpiresAt().isBefore(Instant.now())) {
	            throw new JwtAuthenticationException("Expired JWT token");
	        }
	        return true;
	    } catch (Exception e) {
	        throw new JwtAuthenticationException("Malformed JWT token: " + e.getMessage());
	    }
	}
	/**
     * Extracts the username from the provided JWT token.
     * @param token JWT token from which to extract the username.
     * @return username extracted from the token.
     */
    public String getUsernameFromToken(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        Long userId = Long.valueOf(jwt.getSubject());
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getEmail();

    }
}
