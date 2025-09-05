package com.example.GroupWork_4.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String firstname;
    private String lastname;
    @Column(nullable = false,unique = true)
    private String username;

    private LocalDate birthDate;

    public User(String firstname, String lastname, String username, LocalDate birthDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.birthDate = birthDate;
    }

    public User() {

    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + firstname + '\'' +
                ", surname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
