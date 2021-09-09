package it.eng.snam.summer.dmsmisuraservice.controller;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.mapOf;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        Map<String, String> errors = ex.getBindingResult().getAllErrors().stream()
                .collect(Collectors.toMap(e -> ((FieldError) e).getField(), e -> e.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = ex.getBindingResult().getAllErrors().stream()
                .collect(Collectors.toMap(e -> ((FieldError) e).getField(), e -> e.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { HttpClientErrorException.class })
    protected ResponseEntity<Object> handleHttpError(HttpClientErrorException ex, WebRequest request) {
        return new ResponseEntity<>(Entity.parseJson(ex.getResponseBodyAsString()), ex.getStatusCode());
    }

    @ExceptionHandler(value = { ResponseStatusException.class })
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        ResponseStatusException e = (ResponseStatusException) ex ;
        Object msg = e.getReason();
        try {
            msg = new ObjectMapper().readValue( msg.toString() , Object.class);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return new ResponseEntity<>( mapOf("message",  msg  , "timestamp", Instant.now().toString() , "status", e.getStatus().getReasonPhrase() )  , e.getStatus());
        //return ResponseEntity.badRequest().body(  e.getReason() );
    }

}
