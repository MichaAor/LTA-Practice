package com.lta.apilibrarybid.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message,Throwable error){
        super(message,error);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
