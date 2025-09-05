package com.example.GroupWork_4.model;

import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String createdByUsername;

    // Constructors
    public Post() {}
    public Post(String text, String createdByUsername) {
        this.text = text;
        this.createdByUsername = createdByUsername;
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

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", createdByUsername='" + createdByUsername + '\'' +
                '}';
    }
}
