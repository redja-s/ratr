package com.ratr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ratr.film.FilmRepository;
import com.ratr.film.dto.FilmDto;
import com.ratr.film.exception.FilmExistsException;
import com.ratr.film.exception.FilmNotFoundException;
import com.ratr.film.mapper.EntityMapper;
import com.ratr.film.service.FilmService;
import com.ratr.model.film.Film;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmServiceTests {

  private final EntityMapper entityMapper = EntityMapper.INSTANCE;
  @MockBean private FilmRepository filmRepository;
  @Autowired private FilmService filmService;

  private static final UUID RANDOM_UUID = UUID.fromString("620a0649-7759-4c95-9cd4-03d968ee9e9a");

  @Test
  void testGetAllFilms() {
    Film expectedFilm = filmBuilder1();

    when(filmRepository.findAll()).thenReturn(Collections.singletonList(expectedFilm));

    List<FilmDto> films = filmService.getAllFilms();

    assertEquals(1, films.size());
    assertEquals(expectedFilm.getTitle(), films.get(0).getTitle());
    assertEquals(expectedFilm.getDirectorName(), films.get(0).getDirectorName());
    assertEquals(expectedFilm.getReleaseYear(), films.get(0).getReleaseYear());
  }

  @Test
  void testGetFilmsByTitle() {
    String commonTitle = "Movie";
    Film film1 =
        Film.builder().title(commonTitle).directorName("John Doe").releaseYear(1995).build();

    Film film2 =
        Film.builder().title(commonTitle).directorName("Jane Doe").releaseYear(1997).build();

    when(filmRepository.findFilmsByTitle(commonTitle)).thenReturn(Arrays.asList(film1, film2));

    List<FilmDto> films = filmService.getFilmsByTitle(commonTitle);

    assertEquals(2, films.size());
    assertEquals(commonTitle, films.get(0).getTitle());
    assertEquals(commonTitle, films.get(1).getTitle());
  }

  @Test
  void testGetEmptyFilms() {
    when(filmRepository.findAll()).thenReturn(Collections.emptyList());
    List<FilmDto> films = filmService.getAllFilms();

    assertEquals(0, films.size());
  }

  @Test
  void testStoreFilms() throws FilmExistsException {
    Film filmReturned = filmBuilder1();
    FilmDto filmStored = filmDtoBuilder1();

    when(filmRepository.saveAndFlush(any(Film.class))).thenReturn(filmReturned);

    FilmDto response = filmService.storeFilm(filmStored);
    assertEquals(filmReturned.getDirectorName(), response.getDirectorName());
    assertEquals(filmReturned.getReleaseYear(), response.getReleaseYear());
    assertEquals(filmReturned.getTitle(), response.getTitle());
    assertNotNull(response.getId());
  }

  @Test
  void testStoreDuplicateFilm() {
    FilmDto filmToSave = filmDtoBuilder();
    when(filmRepository.saveAndFlush(any(Film.class)))
        .thenThrow(DataIntegrityViolationException.class);

    assertThrows(
        FilmExistsException.class,
        () -> {
          filmService.storeFilm(filmToSave);
        });
  }

  @Test
  void testUpdateFilmById() throws FilmNotFoundException {
    final Film filmToUpdate = filmBuilder1();

    final FilmDto request = filmDtoBuilder1();
    request.setTitle("Updated Title");

    when(filmRepository.findById(RANDOM_UUID)).thenReturn(Optional.of(filmToUpdate));

    filmToUpdate.setTitle("Updated Title");
    when(filmRepository.saveAndFlush(any(Film.class))).thenReturn(filmToUpdate);

    FilmDto response = filmService.updateFilmById(String.valueOf(RANDOM_UUID), request);

    verify(filmRepository).saveAndFlush(any(Film.class));

    assertEquals(response.getTitle(), request.getTitle());
  }

  private FilmDto filmDtoBuilder() {
    return FilmDto.builder().title("Title 1").releaseYear(1994).directorName("John Doe").build();
  }

  private FilmDto filmDtoBuilder1() {
    return FilmDto.builder()
        .title("500 Days of Summer")
        .directorName("Marc Preston Webb")
        .releaseYear(2009)
        .build();
  }

  private Film filmBuilder1() {
    return Film.builder()
        .id(RANDOM_UUID)
        .title("500 Days of Summer")
        .directorName("Marc Preston Webb")
        .releaseYear(2009)
        .build();
  }
}
