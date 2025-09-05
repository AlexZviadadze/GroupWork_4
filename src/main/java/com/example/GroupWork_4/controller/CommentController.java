package com.example.GroupWork_4.controller;

import com.example.GroupWork_4.model.Comment;
import com.example.GroupWork_4.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) { this.commentService = commentService; }

    @PostMapping
    public Comment addComment(@RequestParam String username, @RequestParam Long postId, @RequestParam String text) {
        return commentService.addComment(username, postId, text);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@RequestParam String username, @PathVariable Long id) {
        commentService.deleteComment(username, id);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@RequestParam String username, @PathVariable Long id, @RequestParam String text) {
        return commentService.updateComment(username, id, text);
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPost(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }
}