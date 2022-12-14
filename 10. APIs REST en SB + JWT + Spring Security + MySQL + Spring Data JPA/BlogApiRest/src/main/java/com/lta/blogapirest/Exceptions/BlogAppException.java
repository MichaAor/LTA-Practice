package com.lta.blogapirest.Exceptions;

import org.springframework.http.HttpStatus;

public class BlogAppException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public BlogAppException(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }
}
