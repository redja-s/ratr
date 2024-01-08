package com.ratr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratr.film.FilmController;
import com.ratr.film.FilmRepository;
import com.ratr.film.FilmService;
import com.ratr.film.model.FilmResponse;
import com.ratr.model.film.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class FilmControllerTests {

    @MockBean
    private FilmRepository filmRepository;

    @MockBean
    private FilmService filmService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private FilmController filmController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFilms() throws Exception {
        List<FilmResponse> expectedResponse = Collections.singletonList(
                FilmResponse.builder().directorName("Marc Preston Webb").filmTitle("500 Days of Summer").releaseYear(2009).build()
        );

        when(filmService.getAllFilms()).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/films")
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(expectedResponse.size()));
    }


    @Test
    void testGetFilmsWithTitle() {
        String filmTitle = "SampleTitle";
        List<Film> films =  List.of(
        );

        // You define the behaviour of the filmRepository mock
        // This line is saying if I call this method in the repository then the return object is a List<String>
        when(filmRepository.findFilmsByTitle(filmTitle)).thenReturn(films);

        // Here, we run the actual test
        ResponseEntity<List<Film>> response = filmController.getFilmsByTitle(filmTitle);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(films, response.getBody());
    }
}
