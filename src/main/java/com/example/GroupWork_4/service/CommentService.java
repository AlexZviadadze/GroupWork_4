package com.example.GroupWork_4.service;

import com.example.GroupWork_4.model.Comment;
import com.example.GroupWork_4.repository.CommentRepository;
import com.example.GroupWork_4.repository.PostRepository;
import com.example.GroupWork_4.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepo;
    private final PostRepository postRepo;
    private final UserRepository userRepo;

    public CommentService(CommentRepository commentRepo, PostRepository postRepo, UserRepository userRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    public void createComment(String username, Long postId, String text) {

        if (!postRepo.existsById(postId)) throw new RuntimeException("Post does not exist!");
        commentRepo.save(new Comment(text, postId, username));
    }

    public Comment updateComment(Long commentId, String username, String newText) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        if (!comment.getCreatedByUsername().equals(username)) throw new RuntimeException("Not your comment!");
        comment.setText(newText);
        return commentRepo.save(comment);
    }

    public void deleteComment(Long commentId, String username) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        if (!comment.getCreatedByUsername().equals(username)) throw new RuntimeException("Not your comment!");
        commentRepo.delete(comment);
    }

    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepo.findByPostId(postId);
    }
}
