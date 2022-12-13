package com.lta.blogapirest.Exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String nameResource;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String nameResource,String fieldName,long fieldValue){
        super(String.format("%s Not found With: %s : '%s' ",nameResource,fieldName,fieldValue));
        this.nameResource = nameResource;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
