package com.micro.userservice.exception;

public class UserDoesNotExistsException extends RuntimeException{

    public UserDoesNotExistsException(String message){
        super(message);
    }
}
