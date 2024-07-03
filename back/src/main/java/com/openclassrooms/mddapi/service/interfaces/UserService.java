package com.openclassrooms.mddapi.service.interfaces;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.requests.UpdateUserRequest;
import com.openclassrooms.mddapi.model.User;

public interface UserService {
	
	 /**
     * Registers a new user with email, username, and password.
     *
     * @param email the email of the new user
     * @param name the username of the new user
     * @param password the password of the new user
     * @return the registered User entity
     * @throws ConflictException if the email or username already exists
     */
  	User registerUser(String email, String name, String password);
  	
  	/**
     * Authenticates a user by email or username and password.
     *
     * @param email the email or username of the user
     * @param password the password of the user
     * @return true if authentication is successful, false otherwise
     * @throws AuthenticationException if the authentication fails
     */
    boolean authenticateUser(String email, String password);
    
    /**
     * Retrieves a user by email.
     *
     * @param email the email of the user to find
     * @return an Optional containing the User if found
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Retrieves detailed information about a user by email.
     *
     * @param email the email of the user
     * @return an Optional containing the mapped UserDto if the user is found
     */
    Optional<UserDto> getUserDetails(String email);
    
    /**
     * Retrieves a user by username.
     *
     * @param username the username of the user to find
     * @return an Optional containing the User if found
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Loads a user by username or email to support Spring Security authentication.
     *
     * @param username the username or email of the user
     * @return UserDetails used for authenticating the user
     * @throws UsernameNotFoundException if the user is not found
     */
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    
    /**
     * Retrieves detailed information about a user by ID.
     *
     * @param userId the ID of the user to find
     * @return a UserDto containing the details of the user
     * @throws NotFoundException if the user is not found
     */
    UserDto getUserById(Long userId);
    
    /**
     * Updates the details of the current authenticated user.
     *
     * @param updateUserRequest DTO containing the updated user details
     * @return a UserDto containing the updated details of the user
     * @throws ConflictException if the email or username already exists
     * @throws CustomException if the user is not found
     */
    UserDto updateUser(UpdateUserRequest updateUserRequest);
    
}
