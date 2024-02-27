package com.piseth.java.school.phoneshop.phoneshop.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor

public class ApiException extends RuntimeException{
    private final HttpStatus status;    // Error status/code
    private final String message;       // show error message from status/code
}
