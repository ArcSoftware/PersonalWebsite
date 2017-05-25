package com.ArcSoftware.PersonalWebsite.services;

import com.ArcSoftware.PersonalWebsite.entities.Authority;
import com.ArcSoftware.PersonalWebsite.entities.User;
import com.ArcSoftware.PersonalWebsite.repositories.AuthorityRepo;
import com.ArcSoftware.PersonalWebsite.repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Jake on 5/25/17.
 */
@Service
public class UserService {
    UserRepo users;
    AuthorityRepo authorities;
    PasswordEncoder encoder;

    public UserService(UserRepo users, AuthorityRepo authorities, PasswordEncoder encoder) {
        this.users = users;
        this.authorities = authorities;
        this.encoder = encoder;
    }

    public void createUser(String username, String password, String passwordConfirmation, boolean admin) {
        User existingUser = users.findFirstByUsername(username);

        if ((existingUser == null) && password.equals(passwordConfirmation)) {
            User newUser = new User(username, encoder.encode(password));
            Authority authority = new Authority("ROLE_USER", newUser);

            users.save(newUser);
            authorities.save(authority);

            if (admin) {
                authority = new Authority("ROLE_ADMIN", newUser);
                authorities.save(authority);
            }
        }
    }
}
