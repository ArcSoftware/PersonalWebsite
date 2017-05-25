package com.ArcSoftware.PersonalWebsite.repositories;

import com.ArcSoftware.PersonalWebsite.entities.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Jake on 5/25/17.
 */
public interface MessageRepo extends CrudRepository <Message, Integer>{
    List<Message> findAll();
    List<Message> findFirst5ByOrderByIdDesc();
}
