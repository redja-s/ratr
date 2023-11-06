package com.ratr.film;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratr.model.GenericJsonResponse;
import com.ratr.model.film.Film;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Data
public class FilmController {

    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);
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
        String checkFilm = filmRepository.getFilmIdByTitleDirectorAndReleaseYear(
                film.getTitle(), film.getDirectorName(), film.getReleaseYear()
        );

        if (checkFilm != null) {
            GenericJsonResponse error = GenericJsonResponse.builder()
                    .message("Film already exists!")
                    .build();

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(error);
        }

        Film newFilm = filmRepository.save(film);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newFilm.getId())
                .toUri();

        return ResponseEntity.created(location).body(newFilm);
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
