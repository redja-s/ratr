package com.ratr.film.controller;

import java.util.List;
import com.ratr.film.dto.FilmDto;
import com.ratr.film.exception.FilmExistsException;
import com.ratr.film.exception.FilmNotFoundException;
import com.ratr.film.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/films")
@Slf4j
@RequiredArgsConstructor
@Validated
public class FilmController {

	private final FilmService filmService;

	@GetMapping
	public ResponseEntity<List<FilmDto>> getAllFilms() {
		log.info("Retrieving all films");
		final var allFilms = filmService.getAllFilms();
		return ResponseEntity.ok(allFilms);
	}

	@PostMapping
	public ResponseEntity<FilmDto> createFilm(@RequestBody FilmDto filmDto)
		throws FilmExistsException {
		log.info("Creating film");
		final var storedFilm = filmService.storeFilm(filmDto);
		return ResponseEntity.ok(storedFilm);
	}

	@DeleteMapping("/{filmIdToDelete}")
	public ResponseEntity<Void> deleteFilmByID(@PathVariable String filmIdToDelete) {
		log.info(String.format("Deleting film [%s]", filmIdToDelete));
		filmService.removeFilmById(filmIdToDelete);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{filmId}")
	public ResponseEntity<FilmDto> updateFilmById(@PathVariable String filmId,
		@RequestBody FilmDto filmDto) throws FilmNotFoundException {
		log.info(String.format("Updating film [%s]", filmId));
		final var updatedFilm = filmService.updateFilmById(filmId, filmDto);
		return ResponseEntity.ok(updatedFilm);
	}

	@GetMapping("/{numOfFilms}")
	public ResponseEntity<List<FilmDto>> getTopNFilms(@PathVariable int numOfFilms) {
		log.info("Retrieving top [] films", numOfFilms);
		final var allFilms = filmService.getAllFilms();
		return ResponseEntity.ok(allFilms.stream().limit(numOfFilms).toList());
	}
}
