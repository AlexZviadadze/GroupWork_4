package com.example.GroupWork_4.model;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 999)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private  User author;

    public Post(String text, User user) {
        this.text=text;
        this.author = user;
    }

    public Post() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", author=" + (author != null ? author.getUsername() : "null") +
                '}';
    }
}
