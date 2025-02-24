package com.micro.hotelservice.exceptions;

public class ResourceNotFoundExcetion  extends RuntimeException{

    public ResourceNotFoundExcetion(String message){
        super(message);
    }
}
