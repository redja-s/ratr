package com.ratr.service;

import com.ratr.film.FilmRepository;
import com.ratr.film.dto.FilmDto;
import com.ratr.film.exception.FilmExistsException;
import com.ratr.film.mapper.EntityMapper;
import com.ratr.film.service.FilmService;
import com.ratr.model.film.Film;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmServiceTests {

    private final EntityMapper entityMapper = EntityMapper.INSTANCE;
    @MockBean
    private FilmRepository filmRepository;
    @Autowired
    private FilmService filmService;

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
        Film film1 = Film.builder()
                .title(commonTitle)
                .directorName("John Doe")
                .releaseYear(1995)
                .build();

        Film film2 = Film.builder()
                .title(commonTitle)
                .directorName("Jane Doe")
                .releaseYear(1997)
                .build();

        when(filmRepository.findFilmsByTitle(commonTitle)).thenReturn(Arrays.asList(film1, film2));

        List<FilmDto> films = filmService.getFilmByTitle(commonTitle);

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
        FilmDto filmToSave = filmDtoBuilder();

        Film film = entityMapper.mapDtoToEntity(filmToSave);
        when(filmRepository.saveAndFlush(any(Film.class))).thenReturn(film);

        FilmDto response = filmService.storeFilm(filmToSave);
        assertEquals(filmToSave.getDirectorName(), response.getDirectorName());
        assertEquals(filmToSave.getReleaseYear(), response.getReleaseYear());
        assertEquals(filmToSave.getTitle(), response.getTitle());
        assertNotNull(response.getId());
    }

    @Test
    void testStoreDuplicateFilm() {
        FilmDto filmToSave = filmDtoBuilder();
        when(filmRepository.saveAndFlush(any(Film.class))).thenThrow(DataIntegrityViolationException.class);

        assertThrows(FilmExistsException.class, () -> {
            filmService.storeFilm(filmToSave);
        });
    }

    @Test
    void testUpdateFilm() {
        FilmDto filmToSave = filmDtoBuilder();
        when(filmRepository.save(any(Film.class))).thenThrow(DataIntegrityViolationException.class);

        assertThrows(FilmExistsException.class, () -> {
            filmService.storeFilm(filmToSave);
        });
    }

    private FilmDto filmDtoBuilder() {
        return FilmDto.builder()
                .title("Title 1")
                .releaseYear(1994)
                .directorName("John Doe")
                .build();
    }

    private Film filmBuilder1() {
        return Film.builder()
                .title("500 Days of Summer")
                .directorName("Marc Preston Webb")
                .releaseYear(2009)
                .build();
    }
}
