package com.micro.userservice.exception;

import com.micro.userservice.payload.ApiResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException exception){

        ApiResponse response = ApiResponse.Builder.builder()
                                                    .setSuccess(false)
                                                    .setMessage(exception.getMessage())
                                                    .setStatus(HttpStatus.NOT_FOUND)
                                                    .build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ApiResponse> handlerUserDoesNotExistsException(UserDoesNotExistsException exception){

        ApiResponse response =  ApiResponse.Builder.builder()
                .setSuccess(false)
                .setMessage(exception.getMessage())
                .setStatus(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
