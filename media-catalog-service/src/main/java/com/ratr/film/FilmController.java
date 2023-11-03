package com.ratr.film;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratr.model.film.Film;
import com.ratr.people.PeopleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Data
public class FilmController {

    private FilmRepository filmRepository;
    private PeopleRepository peopleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public FilmController(FilmRepository filmRepository, PeopleRepository peopleRepository) {
        this.filmRepository = filmRepository;
        this.peopleRepository = peopleRepository;
    }

    @GetMapping("/films")
    public String getAllFilms() throws JsonProcessingException {

        List<Film> allFilms = filmRepository.findAll();
        return objectMapper.writeValueAsString(allFilms);
    }

    @GetMapping("/films/director/{directorName}")
    public String getFilmsByDirector(@PathVariable String directorName) throws JsonProcessingException {
        directorName = directorName.replace("%20", " ");
        List<String> filmsByDirector = filmRepository.findByDirectorName(directorName);

        return objectMapper.writeValueAsString(filmsByDirector);
    }

    // @GetMapping("/films/{id}")
    // public ResponseEntity<Film> getFilmById(@PathVariable UUID id) {
    //     Optional<Film> film = filmRepository.findById(id);
    //     return film.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    // }

     @PostMapping("/films")
     public Film createFilm(@RequestBody Film film) {
        return filmRepository.save(film);
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
