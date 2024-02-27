package com.piseth.java.school.phoneshop.phoneshop.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException{ // customize from Exception


/*    public ResourceNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }*/
    public ResourceNotFoundException(String resourceName, Long id) {
        super(HttpStatus.NOT_FOUND, String.format("%s with id = %d not found", resourceName, id));
    }
}
