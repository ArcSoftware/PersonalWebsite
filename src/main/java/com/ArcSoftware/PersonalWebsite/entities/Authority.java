package com.ArcSoftware.PersonalWebsite.entities;

import javax.persistence.*;

/**
 * Created by Jake on 5/25/17.
 */
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String roleName;

    @ManyToOne
    User user;

    public Authority(String roleName, User user) {
        this.roleName = roleName;
        this.user = user;
    }

    public Authority() {
    }
}
