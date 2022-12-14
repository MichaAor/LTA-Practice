package com.lta.blogapirest.Exceptions;

import com.lta.blogapirest.DTO.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice   //Permite manejo de excepciones de toda la app
public class GlobalExceptionHandler {
    public ResponseEntity<ErrorDetails> handleResourcesNotFound(ResourceNotFoundException exception
            , WebRequest request){
        ErrorDetails ed = new ErrorDetails(new Date(),exception.getMessage()
                ,request.getDescription(false));
    return new ResponseEntity<>(ed, HttpStatus.NOT_FOUND);
    }
}
