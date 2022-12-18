package com.lta.blogapirest.Exceptions;

import com.lta.blogapirest.DTO.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice   //Permite manejo de excepciones de toda la app
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handlerResourcesNotFound(ResourceNotFoundException exception
            , WebRequest request){
        ErrorDetails ed = new ErrorDetails(new Date(),exception.getMessage()
                ,request.getDescription(false));
    return new ResponseEntity<>(ed, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAppException.class)
    public ResponseEntity<ErrorDetails> handlerBlogException(BlogAppException exception
            , WebRequest request){
        ErrorDetails ed = new ErrorDetails(new Date(),exception.getMessage()
                ,request.getDescription(false));
        return new ResponseEntity<>(ed, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handlerGlobalException(Exception exception
            , WebRequest request){
        ErrorDetails ed = new ErrorDetails(new Date(),exception.getMessage()
                ,request.getDescription(false));
        return new ResponseEntity<>(ed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
            , HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error -> {
            String nameField = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(nameField+" error",message);
        }));
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
