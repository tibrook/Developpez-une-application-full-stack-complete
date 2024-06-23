package com.openclassrooms.mddapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 /**
     * Finds a user by their email address.
     * @param email the email address to search for
     * @return an Optional containing the User if found, or empty otherwise
     */
    Optional<User> findByEmail(String email);
    /**
     * Finds a user by their username.
     * @param username the username to search for
     * @return an Optional containing the User if found, or empty otherwise
     */
    Optional<User> findByUsername(String username);
    /**
     * Checks if an email exists in the database and is not associated with the provided user ID.
     * @param email the email address to check
     * @param id the ID of the user to exclude from the search
     * @return true if the email exists and does not belong to the specified user ID, false otherwise
     */
    boolean existsByEmailAndIdNot(String email, Long id);
    /**
     * Checks if a username exists in the database and is not associated with the provided user ID.
     * @param username the username to check
     * @param id the ID of the user to exclude from the search
     * @return true if the username exists and does not belong to the specified user ID, false otherwise
     */
    boolean existsByUsernameAndIdNot(String username, Long id);
}
