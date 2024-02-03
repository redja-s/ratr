package com.ratr.film;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratr.film.dto.FilmDto;
import com.ratr.film.exception.FilmExistsException;
import com.ratr.film.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<List<FilmDto>> getAllFilms() {
        return ResponseEntity.ok(filmService.getAllFilms());
    }

    @PostMapping
    public ResponseEntity<FilmDto> createFilm(@RequestBody FilmDto filmDto) throws FilmExistsException {
        filmService.storeFilm(filmDto);
        return ResponseEntity.ok(filmDto);
    }

    @PutMapping("/films")
    public ResponseEntity<FilmDto> updateFilm(@RequestBody) {}

    // @PutMapping("/films/{id}")
    // public ResponseEntity<Film> updateFilm(@PathVariable UUID id, @RequestBody
    // Film updatedFilm) {
    // Optional<Film> film = filmRepository.findById(id);
    // if (film.isPresent()) {
    // updatedFilm.setId(id);
    // Film savedFilm = filmRepository.save(updatedFilm);
    // return ResponseEntity.ok(savedFilm);
    // } else {
    // return ResponseEntity.notFound().build();
    // }
    // }

    // @DeleteMapping("/films/{id}")
    // public ResponseEntity<Void> deleteFilm(@PathVariable UUID id) {
    // Optional<Film> film = filmRepository.findById(id);
    // if (film.isPresent()) {
    // filmRepository.deleteById(id);
    // return ResponseEntity.noContent().build();
    // } else {
    // return ResponseEntity.notFound().build();
    // }
    // }
}
