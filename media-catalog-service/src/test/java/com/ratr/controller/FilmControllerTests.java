package com.ratr.controller;

import com.ratr.film.FilmController;
import com.ratr.film.FilmRepository;
import com.ratr.model.film.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FilmControllerTests {

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private MockMvc mockMvc;

    @InjectMocks
    private FilmController filmController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Unit tests
    @Test
    void testGetFilms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/films"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetFilmsWithTitle() {
        String filmTitle = "SampleTitle";
        List<Film> films = new ArrayList<>();

        // You define the behaviour of the filmRepository mock
        // This line is saying if I call this method in the repository then the return object is a List<String>
        when(filmRepository.findFilmsByTitle(filmTitle)).thenReturn(films);

        // Here, we run the actual test
        ResponseEntity<List<Film>> response = filmController.getFilmsWithTitle(filmTitle);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(films, response.getBody());
    }
}
