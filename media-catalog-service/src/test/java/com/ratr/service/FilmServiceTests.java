package com.ratr.service;

import com.ratr.film.FilmRepository;
import com.ratr.film.FilmService;
import com.ratr.film.model.FilmResponse;
import com.ratr.model.film.Film;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FilmServiceTests {

    @MockBean
    private FilmRepository filmRepository;

    @Autowired
    private FilmService filmService;

    @Test
    void testGetAllFilms() {
        Film expectedFilm = new Film();
        expectedFilm.setReleaseYear(2009);
        expectedFilm.setDescription("Tom revisits the approximate one year he shared with Summer, the girl he thought he could spend the rest of his life with. She, on the other hand, does not believe in relationships or boyfriends.");
        expectedFilm.setTitle("500 Days of Summer");
        expectedFilm.setDirectorName("Marc Preston Webb");

        when(filmRepository.findAll()).thenReturn(Collections.singletonList(expectedFilm));

        List<FilmResponse> films = filmService.getAllFilms();

        assertEquals(1, films.size());
    }
}
