package com.js.ratr.film;

import com.js.lib.model.Film;
import com.js.lib.model.Genre;
import com.js.lib.model.Person;
import com.js.ratr.people.PeopleRepository;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Data
public class FilmController {

    private final FilmRepository filmRepository;
    private final PeopleRepository peopleRepository;

    @GetMapping("/films")
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @GetMapping("/films/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable UUID id) {
        Optional<Film> film = filmRepository.findById(id);
        return film.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/films")
    public Film createFilm(@RequestBody Film film) {
        String directorName = film.getDirector().getName();
        LocalDate directorDob = film.getDirector().getDateOfBirth();
        Optional<Person> directorOptional = peopleRepository.findByNameAndDateOfBirth(directorName, directorDob);

        if (directorOptional.isPresent()) {
            Person director = directorOptional.get();
            film.getDirector().setId(director.getId());
        }

        return filmRepository.save(film);
    }

    @PutMapping("/films/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable UUID id, @RequestBody Film updatedFilm) {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isPresent()) {
            updatedFilm.setId(id);
            Film savedFilm = filmRepository.save(updatedFilm);
            return ResponseEntity.ok(savedFilm);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/films/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable UUID id) {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isPresent()) {
            filmRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
