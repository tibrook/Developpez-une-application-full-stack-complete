package com.openclassrooms.mddapi.model;

import java.security.Timestamp;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "Subscriptions", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "topic_id"})
})
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Column(name = "created_at")
    private Timestamp createdAt;
}