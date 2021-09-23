package com.example.demo.persona.infraestructure;

import com.example.demo.persona.infraestructure.exception.BeanNotFoundException;
import com.example.demo.persona.infraestructure.exception.BeanUnprocesableException;
import com.example.demo.persona.infraestructure.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//@ControllerAdvice
// @RestController
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(com.example.demo.persona.infraestructure.exception.BeanNotFoundException.class)
    public final ResponseEntity<com.example.demo.persona.infraestructure.exception.ExceptionResponse> handleNotFoundException(BeanNotFoundException ex, WebRequest request) {
        com.example.demo.persona.infraestructure.exception.ExceptionResponse exceptionResponse = new com.example.demo.persona.infraestructure.exception.ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<com.example.demo.persona.infraestructure.exception.ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(com.example.demo.persona.infraestructure.exception.BeanUnprocesableException.class)
    public final ResponseEntity<com.example.demo.persona.infraestructure.exception.ExceptionResponse> handleUnprocesableException(BeanUnprocesableException ex, WebRequest request) {
        com.example.demo.persona.infraestructure.exception.ExceptionResponse exceptionResponse = new com.example.demo.persona.infraestructure.exception.ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
    }

}
