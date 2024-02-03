package com.ratr.film.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "The film already exists")
public class FilmExistsException extends Exception {
    public FilmExistsException(String message) {
        super(message);
    }
}
