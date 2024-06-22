package com.openclassrooms.mddapi.service;

import java.util.Collections;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.requests.UpdateUserRequest;
import com.openclassrooms.mddapi.exception.AuthenticationException;
import com.openclassrooms.mddapi.exception.ConflictException;
import com.openclassrooms.mddapi.exception.CustomException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;
	
	
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.passwordEncoder=passwordEncoder;
		this.modelMapper=modelMapper;
	}
    public User registerUser(String email, String username, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ConflictException("Email already exists");
        }
        if(userRepository.findByUsername(username).isPresent()) {
            throw new ConflictException("Username already exists");
        }
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }
    
    @Override
    public Optional<User> findByUsername(String username) { 
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean authenticateUser(String usernameOrEmail, String password) {
    	User user = userRepository.findByEmail(usernameOrEmail)
                .orElseGet(() -> userRepository.findByUsername(usernameOrEmail)
                .orElseThrow(() -> new AuthenticationException("Invalid username or email")));
    	if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Invalid password");
        }
        return true;
    }
    public Optional<UserDto> getUserDetails(String email) {
        return findByEmail(email)
                .map(user -> modelMapper.map(user, UserDto.class));
    }
   
 
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(usernameOrEmail)
                .orElseGet(() -> userRepository.findByUsername(usernameOrEmail)
                .orElseThrow(() -> new AuthenticationException("User not found with email or username: " + usernameOrEmail)));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
    
    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UpdateUserRequest updateUserRequest) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    Long userId = Long.valueOf(authentication.getName());
	    User user = userRepository.findById(userId)
    	            .orElseThrow(() -> new CustomException("User not found"));
	    if (userRepository.existsByEmailAndIdNot(updateUserRequest.getEmail(), userId)) {
	        throw new ConflictException("Email already exists");
	    }
	    if (userRepository.existsByUsernameAndIdNot(updateUserRequest.getUsername(), userId)) {
	        throw new ConflictException("Username already exists");
	    }
	    user.setUsername(updateUserRequest.getUsername());
	    user.setEmail(updateUserRequest.getEmail());
     
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }
}

