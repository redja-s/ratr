package com.ratr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratr.film.FilmController;
import com.ratr.film.FilmRepository;
import com.ratr.film.dto.FilmDto;
import com.ratr.film.mapper.EntityMapper;
import com.ratr.film.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FilmControllerTests {

    @MockBean
    private FilmRepository filmRepository;

    @MockBean
    private FilmService filmService;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final EntityMapper entityMapper = EntityMapper.INSTANCE;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        FilmController filmController = new FilmController(filmService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(filmController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    @Test
    void testGetAllFilms() throws Exception {
        List<FilmDto> expectedResponse = Collections.singletonList(
                FilmDto.builder()
                        .directorName("Marc Preston Webb")
                        .title("500 Days of Summer")
                        .releaseYear(2009)
                        .build()
        );

        when(filmService.getAllFilms()).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(expectedResponse.size()));
    }

    @Test
    void testStoreFilm() throws Exception {
        FilmDto filmToStore = FilmDto.builder()
                .directorName("John Smith")
                .title("A Long Summer Day")
                .releaseYear(1998)
                .build();

        when(filmService.storeFilm(filmToStore)).thenReturn(filmToStore);

        MvcResult response = mockMvc.perform(post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmToStore)))
                .andExpect(status().isOk())
                .andReturn();

        FilmDto dto = objectMapper.readValue(response.getResponse().getContentAsString(), FilmDto.class);
        assertEquals(filmToStore.getTitle(), dto.getTitle());
    }
}
