package com.example.GroupWork_4.service;

import com.example.GroupWork_4.model.Post;
import com.example.GroupWork_4.model.User;
import com.example.GroupWork_4.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class PostService {

    private  final PostRepository postRepository;
    private  final AuthService authService;

    public  PostService(PostRepository postRepository, AuthService authService){
        this.postRepository = postRepository;
        this.authService = authService;
    }

    public Post createPost(String username, String text){
        User author = authService.validateUser(username);
        Post post = new Post();
        post.setText(text);
        post.setAuthor(author);
        return  postRepository.save(post);
    }

    public void deletePost(String username, Long postId) {
        User user = authService.validateUser(username);
        Post post = postRepository.findById(postId).orElseThrow();
        if (!post.getAuthor().equals(user)) {
            throw new RuntimeException("Cannot delete another user's post");
        }
        postRepository.delete(post);
    }

    public Post updatePost(String username, Long postId, String newText) {
        User user = authService.validateUser(username);
        Post post = postRepository.findById(postId).orElseThrow();
        if (!post.getAuthor().equals(user)) {
            throw new RuntimeException("Cannot edit another user's post");
        }
        post.setText(newText);
        return postRepository.save(post);
    }

    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<Post> getUserPosts(String username, Pageable pageable) {
        return (Page<Post>) postRepository.findByAuthorUsername(username, pageable);
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow();
    }
}
