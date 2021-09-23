package com.example.demo.persona.infraestructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BeanUnprocesableException  extends RuntimeException {
    public BeanUnprocesableException(String message) {
        super(message);
        //System.out.println(message);
    }
}