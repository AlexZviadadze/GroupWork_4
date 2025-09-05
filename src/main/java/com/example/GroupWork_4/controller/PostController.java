package com.example.GroupWork_4.controller;

import com.example.GroupWork_4.model.Post;
import com.example.GroupWork_4.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) { this.postService = postService; }

    @PostMapping
    public Post createPost(@RequestParam String username, @RequestParam String text) {
        return postService.createPost(username, text);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@RequestParam String username, @PathVariable Long id) {
        postService.deletePost(username, id);
    }

    @PutMapping("/{id}")
    public Post updatePost(@RequestParam String username, @PathVariable Long id, @RequestParam String text) {
        return postService.updatePost(username, id, text);
    }

    @GetMapping
    public Page<Post> getAllPosts(Pageable pageable) {
        return postService.getAllPosts(pageable);
    }

    @GetMapping("/user/{username}")
    public Page<Post> getUserPosts(@PathVariable String username, Pageable pageable) {
        return postService.getUserPosts(username, pageable);
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.getPostById(id);
    }
}