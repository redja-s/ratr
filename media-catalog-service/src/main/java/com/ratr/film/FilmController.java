package com.ratr.film;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratr.model.GenericJsonResponse;
import com.ratr.model.film.Film;
import jakarta.validation.ConstraintViolationException;
import lombok.Data;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Data
public class FilmController {

    private FilmRepository filmRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @GetMapping("/films/{filmTitle}")
    public ResponseEntity<List<Film>> getFilmsWithTitle(@PathVariable String filmTitle) {
        return ResponseEntity.ok(filmRepository.findFilmsByTitle(filmTitle));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Endpoint working fine!");
    }

    @GetMapping("/films")
    public ResponseEntity<List<Film>> getAllFilms() throws JsonProcessingException {
        List<Film> allFilms = filmRepository.findAll();
        return ResponseEntity.ok(allFilms);
    }

    @GetMapping("/films/director/{directorName}")
    public String getFilmsByDirector(@PathVariable String directorName) throws JsonProcessingException {
        directorName = directorName.replace("%20", " ");
        List<String> filmsByDirector = filmRepository.findByDirectorName(directorName);

        return objectMapper.writeValueAsString(filmsByDirector);
    }

//    @GetMapping("/films/ids")
//    public String getFilmIdByTitleDirectorAndReleaseYear(String title, String directorName, int releaseYear) throws JsonProcessingException {
//        return objectMapper.writeValueAsString(
//                filmRepository.getFilmIdByTitleDirectorAndReleaseYear(
//                        title, directorName, releaseYear
//                )
//        );
//    }

     @PostMapping("/films")
     public ResponseEntity<?> createFilm(@RequestBody Film film) {
        try {
            Film newFilm = filmRepository.save(film);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newFilm.getId())
                    .toUri();

            return ResponseEntity.created(location).body(newFilm);
        } catch (DataIntegrityViolationException e) {
            GenericJsonResponse response = GenericJsonResponse.builder()
                    .message("Film already exists!")
                    .build();

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(response);
         }
     }

    // @PutMapping("/films/{id}")
    // public ResponseEntity<Film> updateFilm(@PathVariable UUID id, @RequestBody Film updatedFilm) {
    //     Optional<Film> film = filmRepository.findById(id);
    //     if (film.isPresent()) {
    //         updatedFilm.setId(id);
    //         Film savedFilm = filmRepository.save(updatedFilm);
    //         return ResponseEntity.ok(savedFilm);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    // @DeleteMapping("/films/{id}")
    // public ResponseEntity<Void> deleteFilm(@PathVariable UUID id) {
    //     Optional<Film> film = filmRepository.findById(id);
    //     if (film.isPresent()) {
    //         filmRepository.deleteById(id);
    //         return ResponseEntity.noContent().build();
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }
}
