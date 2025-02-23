package com.micro.userservice.controllers;

import com.micro.userservice.entities.User;
import com.micro.userservice.payload.ApiResponse;
import com.micro.userservice.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
       User user1 = userService.saveUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(user1);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
       User user = userService.getUserById(userId);

       return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.getAllUser();
        return ResponseEntity.ok(userList);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable String userId){
        userService.deleteUserById(userId);

        ApiResponse response = ApiResponse.Builder.builder()
                .setSuccess(true)
                .setStatus(HttpStatus.OK)
                .setMessage("User wit id "+userId+" deleted successfully...")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}



























