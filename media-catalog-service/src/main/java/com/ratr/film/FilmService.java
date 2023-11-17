package com.ratr.film;

import com.ratr.model.film.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public List<Film> getFilmsByTitle(String filmTitle) {
        return filmRepository.findFilmsByTitle(filmTitle);
    }

    public List<Film> getFilmsByDirector(String directorName) {
        return filmRepository.findByDirectorName(directorName);
    }

//    public void updateFilmDetailsByUUID(UUID uuid) {
//        return filmRepository.
//    }
}
