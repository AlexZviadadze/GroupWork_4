package com.example.GroupWork_4.repo;

import com.example.GroupWork_4.model.Comment;
import com.example.GroupWork_4.model.Post;
import com.example.GroupWork_4.model.User;
import com.example.GroupWork_4.repository.CommentRepository;
import com.example.GroupWork_4.repository.PostRepository;
import com.example.GroupWork_4.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final UserRepository userRepo;
    private final PostRepository postRepo;
    private final CommentRepository commentRepo;

    public ConsoleRunner(UserRepository userRepo, PostRepository postRepo, CommentRepository commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Console Testing ===");


        User user1 = new User("Alex", "Zviadadze", "alex123", LocalDate.parse("1997-01-21"));
        userRepo.save(user1);
        System.out.println("Created user: " + user1);


        Post post1 = new Post("Hello world!", user1);
        postRepo.save(post1);
        System.out.println("Created post: " + post1);


        Comment comment1 = new Comment("Nice post!", post1, user1);
        commentRepo.save(comment1);
        System.out.println("Created comment: " + comment1);


        post1.setText("Updated Hello world!");
        postRepo.save(post1);
        System.out.println("Updated post: " + post1);


        comment1.setText("Updated comment!");
        commentRepo.save(comment1);
        System.out.println("Updated comment: " + comment1);

        //  delete post
        // postRepo.delete(post1);
        // System.out.println("Deleted post: " + post1);


        postRepo.findByAuthorUsername(user1.getUsername(), PageRequest.of(0, 10))
                .forEach(p -> System.out.println("User's post: " + p));


        postRepo.findAll(PageRequest.of(0, 10)).forEach(p -> System.out.println("All post: " + p));
    }
}
