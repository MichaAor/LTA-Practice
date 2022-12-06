package com.lta.filmsapp.PException;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class FileNotFoundException extends RuntimeException{

}

