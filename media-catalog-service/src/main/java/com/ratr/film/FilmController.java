package com.ratr.film;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratr.film.model.FilmResponse;
import com.ratr.model.film.Film;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  private ObjectMapper objectMapper;

  @Autowired
  public FilmController(FilmService filmService) {
    this.filmService = filmService;
  }

  @GetMapping
  public ResponseEntity<List<FilmResponse>> getAllFilms() {
    return ResponseEntity.ok(filmService.getAllFilms());
  }

  @GetMapping("/{filmTitle}")
  public ResponseEntity<List<Film>> getFilmsByTitle(@PathVariable String title) {
    return ResponseEntity.ok(filmService.getFilmsByTitle(title));
  }

  @GetMapping("/director/{directorName}")
  public ResponseEntity<List<Film>> getFilmsByDirector(@PathVariable String directorName) {
    return ResponseEntity.ok(filmService.getFilmsByDirector(directorName));
  }

  // @GetMapping("/films/ids")
  // public String getFilmIdByTitleDirectorAndReleaseYear(String title, String
  // directorName, int releaseYear) throws JsonProcessingException {
  // return objectMapper.writeValueAsString(
  // filmRepository.getFilmIdByTitleDirectorAndReleaseYear(
  // title, directorName, releaseYear
  // )
  // );
  // }

  // @PostMapping
  // public ResponseEntity<?> createFilm(@RequestBody Film film) {
  // String checkFilm = filmRepository.getFilmIdByTitleDirectorAndReleaseYear(
  // film.getTitle(), film.getDirectorName(), film.getReleaseYear()
  // );
  //
  // if (checkFilm != null) {
  // GenericJsonResponse error = GenericJsonResponse.builder()
  // .message("Film already exists!")
  // .build();
  //
  // return ResponseEntity.status(HttpStatus.CONFLICT)
  // .body(error);
  // }
  //
  // Film newFilm = filmRepository.save(film);
  // URI location = ServletUriComponentsBuilder
  // .fromCurrentRequest()
  // .path("/{id}")
  // .buildAndExpand(newFilm.getId())
  // .toUri();
  //
  // return ResponseEntity.created(location).body(newFilm);
  // }

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
