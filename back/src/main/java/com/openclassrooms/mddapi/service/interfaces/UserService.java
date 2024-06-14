package com.openclassrooms.mddapi.service.interfaces;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.User;

public interface UserService {
  	User registerUser(String email, String name, String password);
    boolean authenticateUser(String email, String password);
    Optional<User> findByEmail(String email);
    Optional<UserDto> getUserDetails(String email);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
