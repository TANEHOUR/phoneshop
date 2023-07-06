package com.piseth.java.school.phoneshop.phoneshop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor

public class ErrorResponse { // customize Error on postman
    private HttpStatus status;
    private String message;
}
