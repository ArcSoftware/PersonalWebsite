package com.ArcSoftware.PersonalWebsite.entities;

import javax.persistence.*;

/**
 * Created by Jake on 5/25/17.
 */

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String message;

    @ManyToOne
    User user;

    public Message(User user, String message) {
        this.message = message;
        this.user = user;
    }

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
