package com.github.dzeko14.DiscoveryClient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some of your parameters, that you've entered, is invalid!")
public class InvalidEntityFieldException extends RuntimeException {
    public InvalidEntityFieldException(String message) {
        super(message);
    }
}
