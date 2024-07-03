package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	/**
     * Finds all comments associated with a given post ID.
     * @param postId the ID of the post for which comments are to be retrieved
     * @return a list of comments for the specified post
     */
    List<Comment> findByPostId(Long postId);
}
