package org.example.testosteron.error;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;

import static org.example.testosteron.constants.TaskErrors.NOT_FOUND;
import static org.example.testosteron.constants.TaskErrors.PARSE_EXCEPTION;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<String> handleParseException(ParseException ex) {
        return new ResponseEntity<>(PARSE_EXCEPTION + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
