package com.ratr.film;

import com.ratr.film.model.FilmResponse;
import com.ratr.model.film.Film;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FilmService {

  private final FilmRepository filmRepository;

  @Autowired
  public FilmService(FilmRepository filmRepository) {
    this.filmRepository = filmRepository;
  }

  public List<FilmResponse> getAllFilms() {
    List<Film> allFilms = filmRepository.findAll();
    return allFilms.stream().map(film -> FilmResponse.builder()
        .releaseYear(film.getReleaseYear())
        .filmTitle(film.getTitle())
        .description(film.getDescription())
        .directorName(film.getDirectorName())
        .build()).collect(Collectors.toList());
  }

  public FilmResponse getFilmById(String filmId) {
    final var film = filmRepository.findById(UUID.fromString(filmId));
    return FilmResponse.builder()
        .description(film.get().getDescription())
        .directorName(film.get().getDirectorName())
        .filmTitle(film.get().getTitle())
        .releaseYear(film.get().getReleaseYear())
        .build();
  }

  public List<Film> getFilmsByTitle(String filmTitle) {
    return filmRepository.findFilmsByTitle(filmTitle);
  }

  public List<Film> getFilmsByDirector(String directorName) {
        return filmRepository.findByDirectorName(directorName);
    }

  public FilmResponse storeFilm()
}
