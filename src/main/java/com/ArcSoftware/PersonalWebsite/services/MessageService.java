package com.ArcSoftware.PersonalWebsite.services;

import com.ArcSoftware.PersonalWebsite.entities.Message;
import com.ArcSoftware.PersonalWebsite.entities.User;
import com.ArcSoftware.PersonalWebsite.repositories.MessageRepo;
import com.ArcSoftware.PersonalWebsite.repositories.UserRepo;
import org.springframework.stereotype.Service;

/**
 * Created by Jake on 5/25/17.
 */
@Service
public class MessageService {
    UserRepo users;
    MessageRepo messages;


    public MessageService(UserRepo users, MessageRepo messages) {
        this.users = users;
        this.messages = messages;
    }

    public void createMessage(String user, String text) {
        User existingUser = users.findFirstByUsername(user);
        Message message = new Message(existingUser, text);
            messages.save(message);
    }

}
