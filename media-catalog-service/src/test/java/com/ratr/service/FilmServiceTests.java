package com.ratr.service;

import com.ratr.film.FilmRepository;
import com.ratr.film.dto.FilmDto;
import com.ratr.film.exception.FilmExistsException;
import com.ratr.film.mapper.EntityMapper;
import com.ratr.film.model.FilmResponse;
import com.ratr.film.service.FilmService;
import com.ratr.model.film.Film;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmServiceTests {

    @MockBean
    private FilmRepository filmRepository;

    @Autowired
    private FilmService filmService;

    private final EntityMapper entityMapper = EntityMapper.INSTANCE;

    @Test
    void testGetAllFilms() {
        Film expectedFilm = Film.builder()
                .title("500 Days of Summer")
                .directorName("Marc Preston Webb")
                .releaseYear(2009)
                .build();

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
        FilmDto filmToSave = FilmDto.builder()
                .title("Title 1")
                .releaseYear(1994)
                .directorName("John Doe")
                .build();

        Film film = entityMapper.mapDtoToEntity(filmToSave);
        when(filmRepository.save(film)).thenReturn(film);

        FilmDto response = filmService.storeFilm(filmToSave);
        assertEquals(filmToSave.getDirectorName(), response.getDirectorName());
        assertEquals(filmToSave.getReleaseYear(), response.getReleaseYear());
        assertEquals(filmToSave.getTitle(), response.getTitle());
    }
}
