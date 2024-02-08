package com.ratr.film.exception;

import com.ratr.film.exception.model.GenericErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FilmExistsException.class)
    protected ResponseEntity<GenericErrorResponse> handleFilmExistsException(FilmExistsException error) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                GenericErrorResponse.builder()
                        .message("Film exists with the given title, director and release year")
                        .exception(FilmExistsException.class.getSimpleName())
                        .build()
        );
    }
}
