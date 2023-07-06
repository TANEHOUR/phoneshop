package com.piseth.java.school.phoneshop.phoneshop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {   // handle all exception
    // catch ApiException this class must extend from class Throwable
    // or Every subclass from Throwable class
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e){

        ErrorResponse errorResponse = new ErrorResponse(e.getStatus(), e.getMessage());
        return ResponseEntity
                .status(e.getStatus())
                .body(errorResponse);

    }

}
