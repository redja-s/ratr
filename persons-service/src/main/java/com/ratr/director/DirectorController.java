package com.ratr.director;

import com.ratr.model.Film;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DirectorController {
    private final DirectorRepository directorRepository;

    @GetMapping("/directors")
    public Iterable<Director> getDirectors() {
        return this.directorRepository.findAll();
    }

    @PostMapping("/films")
    public Film addDirector(@RequestBody Film film) {
        return this.directorRepository.save(film);
    }

    public DirectorController(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }
}
