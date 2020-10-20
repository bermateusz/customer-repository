package com.usermap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "No customer mordy")
public class CustomerDoesNotExistException extends RuntimeException{
    public CustomerDoesNotExistException(String message) {
        super(message);
    }
}
