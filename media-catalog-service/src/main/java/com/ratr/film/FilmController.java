package com.ratr.film;

import com.ratr.model.Film;
import com.ratr.people.PeopleRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    // @GetMapping("/films/{id}")
    // public ResponseEntity<Film> getFilmById(@PathVariable UUID id) {
    //     Optional<Film> film = filmRepository.findById(id);
    //     return film.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    // }

    // @PostMapping("/films")
    // public Film createFilm(@RequestBody Film film) {
    //     String directorName = film.getDirector().getName();
    //     LocalDate directorDob = film.getDirector().getDateOfBirth();
    //     Optional<Person> directorOptional = peopleRepository.findByNameAndDateOfBirth(directorName, directorDob);

    //     if (directorOptional.isPresent()) {
    //         Person director = directorOptional.get();
    //         film.getDirector().setId(director.getId());
    //     }

    //     return filmRepository.save(film);
    // }

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
