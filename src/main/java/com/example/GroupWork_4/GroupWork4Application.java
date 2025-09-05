package com.example.GroupWork_4;

import com.example.GroupWork_4.model.Post;
import com.example.GroupWork_4.model.User;
import com.example.GroupWork_4.service.CommentService;
import com.example.GroupWork_4.service.PostService;
import com.example.GroupWork_4.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;

@SpringBootApplication
public class GroupWork4Application implements CommandLineRunner {

	private final UserService userService;
	private final PostService postService;
	private final CommentService commentService;

	public GroupWork4Application(UserService userService, PostService postService, CommentService commentService) {
		this.userService = userService;
		this.postService = postService;
		this.commentService = commentService;
	}

	public static void main(String[] args) {
		SpringApplication.run(GroupWork4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		User u1 = getOrRegisterUser("Aleksi", "Zviadadze", "alex97", LocalDate.of(1997, 1, 21));
		User u2 = getOrRegisterUser("Nino", "Beridze", "nino95", LocalDate.of(1995, 3, 12));

		System.out.println("Users registered: " + u1.getUsername() + ", " + u2.getUsername());

		Post p1 = postService.createPost(u1, "Hello World!");
		Post p2 = postService.createPost(u1, "Second Post");

		System.out.println("Posts created by " + u1.getUsername() + ": " + p1.getText() + ", " + p2.getText());

		postService.updatePost(p1.getId(), u1.getUsername(), "Updated Hello World!");
		System.out.println("Updated Post 1: " + postService.getPostById(p1.getId()).getText());


		commentService.createComment(u2.getUsername(), p1.getId(), "Nice post!");
		commentService.createComment(u1.getUsername(), p1.getId(), "Thanks!");
		System.out.println("Comments added to post 1.");

		postService.getAllPosts(PageRequest.of(0, 5)).forEach(post ->
				System.out.println("Post: " + post.getText() + " by " + post.getCreatedByUsername())
		);

		postService.getPostsByUser(u1.getUsername()).forEach(post ->
				System.out.println("Alex's Post: " + post.getText())
		);


		// commentService.deleteComment(1L, "nino95");

	}


	private User getOrRegisterUser(String firstName, String lastName, String username, LocalDate birthDate) {
		User existing = userService.getUserByUsername(username);
		if (existing != null) {
			System.out.println("Username already exists! Using existing user: " + username);
			return existing;
		}
		return userService.register(new User(firstName, lastName, username, birthDate));
	}
}
