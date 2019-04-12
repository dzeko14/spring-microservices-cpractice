package com.github.dzeko14.SocialNetwork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Server database is not working properly!")
public class DatabaseNotRespondException extends RuntimeException {
    public DatabaseNotRespondException(String message) {
        super(message);
    }
}
