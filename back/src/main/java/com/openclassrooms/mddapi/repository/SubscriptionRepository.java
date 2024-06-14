package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    void deleteByUserAndTopic(User user, Topic topic);
    boolean existsByUserAndTopic(User user, Topic topic);
}