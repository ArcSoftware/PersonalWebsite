package com.ArcSoftware.PersonalWebsite.repositories;

import com.ArcSoftware.PersonalWebsite.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jake on 5/25/17.
 */
public interface UserRepo extends CrudRepository <User, Integer> {

    User findFirstByUsername(String username);
}
