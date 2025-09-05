package com.example.GroupWork_4.service;

import com.example.GroupWork_4.model.Post;
import com.example.GroupWork_4.model.User;
import com.example.GroupWork_4.repository.PostRepository;
import com.example.GroupWork_4.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


    @Service
    public class PostService {

        private final PostRepository postRepo;
        private final UserRepository userRepo;

        public PostService(PostRepository postRepo, UserRepository userRepo) {
            this.postRepo = postRepo;
            this.userRepo = userRepo;
        }

        public Post createPost(User user, String text) {
            Post post = new Post(user.getUsername(), text);
            return postRepo.save(post);
        }

        public Post updatePost(Long postId, String username, String newText) {
            Post post = postRepo.findById(postId)
                    .orElseThrow(() -> new RuntimeException("Post not found!"));



            post.setText(newText);
            return postRepo.save(post);
        }

        public Post getPostById(Long id) {
            return postRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Post not found!"));
        }

        public List<Post> getAllPosts(Pageable pageable) {
            return postRepo.findAll(pageable).getContent();
        }

        public List<Post> getPostsByUser(String username) {
            return postRepo.findByCreatedByUsername(username);
        }
    }
