package com.ratr.controller;

import com.ratr.film.FilmRepository;
import com.ratr.model.film.Film;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class FilmControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmRepository filmRepository;

    @Test
    public void testCreateFilm() {


    }
}
