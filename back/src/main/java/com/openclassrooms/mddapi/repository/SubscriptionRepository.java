package com.openclassrooms.mddapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	 /**
     * Deletes a subscription for a specific user and topic.
     * @param user the user part of the subscription
     * @param topic the topic part of the subscription
     */
    void deleteByUserAndTopic(User user, Topic topic);
    /**
     * Checks if a subscription exists for a specific user and topic.
     * @param user the user to check the subscription for
     * @param topic the topic to check the subscription against
     * @return true if subscription exists, false otherwise
     */
    boolean existsByUserAndTopic(User user, Topic topic);
    /**
     * Finds a subscription by user and topic.
     * @param user the user in the subscription
     * @param topic the topic in the subscription
     * @return an Optional containing the found subscription if it exists
     */
    Optional<Subscription> findByUserAndTopic(User user, Topic topic);
    /**
     * Finds all subscriptions for a specific user.
     * @param user the user whose subscriptions are to be retrieved
     * @return a list of subscriptions for the specified user
     */
    List<Subscription> findByUser(User user);
}