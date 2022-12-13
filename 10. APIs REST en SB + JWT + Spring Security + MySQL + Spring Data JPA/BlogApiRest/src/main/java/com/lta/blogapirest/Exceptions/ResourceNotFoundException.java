package com.lta.blogapirest.Exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
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
