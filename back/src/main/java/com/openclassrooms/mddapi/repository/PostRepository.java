package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	 /**
     * Retrieves all posts associated with a specific topic.
     * @param topic the Topic object associated with the posts
     * @return a list of posts that belong to the specified topic
     */
    List<Post> findByTopic(Topic topic); 
}