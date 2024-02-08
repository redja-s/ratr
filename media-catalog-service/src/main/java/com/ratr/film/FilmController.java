package com.ratr.film;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratr.film.dto.FilmDto;
import com.ratr.film.exception.FilmExistsException;
import com.ratr.film.service.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/films")
@Slf4j
@RequiredArgsConstructor
@Validated
public class FilmController {

    private final FilmService filmService;

    @GetMapping
    public ResponseEntity<List<FilmDto>> getAllFilms() {
        final var allFilms = filmService.getAllFilms();
        return ResponseEntity.ok(allFilms);
    }

    @PostMapping
    public ResponseEntity<FilmDto> createFilm(@RequestBody FilmDto filmDto) throws FilmExistsException {
        final var storedFilm = filmService.storeFilm(filmDto);
        return ResponseEntity.ok(storedFilm);
    }

    @DeleteMapping("/{filmIdToDelete}")
    public ResponseEntity<Void> deleteFilmByID(@PathVariable String filmIdToDelete) {
        filmService.removeFilmById(filmIdToDelete);
        return ResponseEntity.noContent().build();
    }
}
