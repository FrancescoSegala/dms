package it.eng.snam.summer.dmsmisuraservice.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
                Map<String, String> errors =  ex.getBindingResult()
                                .getAllErrors()
                                .stream()
                                .collect(Collectors.toMap(e -> ((FieldError) e).getField(), e -> e.getDefaultMessage() ));
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }



}
