package com.micro.userservice.services;

import com.micro.userservice.entities.User;
import com.micro.userservice.exception.ResourceNotFoundException;
import com.micro.userservice.exception.UserDoesNotExistsException;
import com.micro.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return List.of(userRepository.findAll().toArray(new User[0]));
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id "+userId+" not found on server !!"));
    }

    @Override
    public boolean deleteUserById(String userId) {
        Optional<User> optional = userRepository.findById(userId);

        if(optional.isEmpty()){
            throw new UserDoesNotExistsException("User does not exist....");
        }
        userRepository.deleteById(userId);

        return true;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }
}
