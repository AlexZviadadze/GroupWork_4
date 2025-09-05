package com.example.GroupWork_4.repository;

import com.example.GroupWork_4.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    default List<Comment> findByPostId() {
        return findByPostId(null);
    }

    List<Comment> findByPostId(Long postId);
}
