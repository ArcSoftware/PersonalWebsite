package com.ArcSoftware.PersonalWebsite.controllers;

import com.ArcSoftware.PersonalWebsite.entities.User;
import com.ArcSoftware.PersonalWebsite.repositories.AuthorityRepo;
import com.ArcSoftware.PersonalWebsite.repositories.UserRepo;
import com.ArcSoftware.PersonalWebsite.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

/**
 * Created by Jake on 5/25/17.
 */
@Controller
public class UserController {
    UserRepo users;
    AuthorityRepo auth;
    UserService userService;

    public UserController(UserRepo users, AuthorityRepo auth, UserService userService) {
        this.users = users;
        this.auth = auth;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        if (users.count() == 0) {
            userService.createUser("admin", "1234", "1234", true);
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/new-user", method = RequestMethod.GET)
    public String newUser() {
        return "registration";
    }

    @RequestMapping(path = "/new-user", method = RequestMethod.POST)
    public String newUser(User user, String passwordConfirm) {
        userService.createUser(user.getUsername(), user.getPassword(), passwordConfirm, false);
        return "redirect:/chatroom";
    }
}
