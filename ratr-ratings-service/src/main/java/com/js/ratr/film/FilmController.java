package com.js.ratr.film;

import com.js.ratr.model.Film;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FilmController {

    private final FilmRepository filmRepository;

    @GetMapping("/films")
    public Iterable<Film> getFilms() {
        return this.filmRepository.findAll();
    }

    @PostMapping("/films")
    public Film addFilm(@RequestBody Film film) {
        return this.filmRepository.save(film);
    }

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }
}
