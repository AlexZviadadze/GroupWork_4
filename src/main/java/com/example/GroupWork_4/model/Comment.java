package com.example.GroupWork_4.model;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private Long postId;
    private String createdByUsername;

    // Constructors
    public Comment() {}
    public Comment(String text, Long postId, String createdByUsername) {
        this.text = text;
        this.postId = postId;
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

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", postId=" + postId +
                ", createdByUsername='" + createdByUsername + '\'' +
                '}';
    }
}
