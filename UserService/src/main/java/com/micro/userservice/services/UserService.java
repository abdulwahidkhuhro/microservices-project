package com.micro.userservice.services;

import com.micro.userservice.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    User getUserById(String userId);

    boolean deleteUserById(String userId);

    boolean updateUser(User user);

}
