package com.openclassrooms.mddapi.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 5000)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

   
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

   
    @Column(name = "updated_at")
    @CreationTimestamp
    private LocalDateTime updatedAt;
}
