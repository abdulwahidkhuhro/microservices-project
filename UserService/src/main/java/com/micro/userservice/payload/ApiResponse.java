package com.micro.userservice.payload;

import org.springframework.http.HttpStatus;

public class ApiResponse {

    private String message;
    private boolean success;

    private HttpStatus status;

    public ApiResponse(Builder builder){
        this.message = builder.message;
        this.status = builder.status;
        this.success = builder.success;
    }

    public static class Builder{
        private String message;
        private boolean success;
        private HttpStatus status;

        public static Builder builder(){
            return new Builder();
        }

        public Builder setMessage(String message){
            this.message = message;
            return this;
        }

        public Builder setSuccess(boolean success){
            this.success = success;
            return this;
        }

        public Builder setStatus(HttpStatus status){
            this.status = status;
            return this;
        }

        public ApiResponse build(){
            return new ApiResponse(this);
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
