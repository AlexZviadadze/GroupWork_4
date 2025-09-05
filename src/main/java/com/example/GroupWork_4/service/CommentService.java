package com.example.GroupWork_4.service;

import com.example.GroupWork_4.model.Comment;
import com.example.GroupWork_4.model.Post;
import com.example.GroupWork_4.model.User;
import com.example.GroupWork_4.repository.CommentRepository;
import com.example.GroupWork_4.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, AuthService authService) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.authService = authService;
    }

    public Comment addComment(String username, Long postId, String text) {
        User user = authService.validateUser(username);
        Post post = postRepository.findById(postId).orElseThrow();
        Comment comment = new Comment();
        comment.setText(text);
        comment.setAuthor(user);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public void deleteComment(String username, Long commentId) {
        User user = authService.validateUser(username);
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if (!comment.getAuthor().equals(user)) {
            throw new RuntimeException("Cannot delete another user's comment");
        }
        commentRepository.delete(comment);
    }

    public Comment updateComment(String username, Long commentId, String newText) {
        User user = authService.validateUser(username);
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if (!comment.getAuthor().equals(user)) {
            throw new RuntimeException("Cannot edit another user's comment");
        }
        comment.setText(newText);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
