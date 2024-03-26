package com.ratr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratr.film.dto.FilmDto;
import com.ratr.film.exception.FilmExistsException;
import com.ratr.film.exception.model.GenericErrorResponse;
import com.ratr.film.mapper.EntityMapper;
import com.ratr.film.service.FilmService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class FilmControllerTests {

    private final EntityMapper entityMapper = EntityMapper.INSTANCE;

    @MockBean
    private FilmService filmService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllFilms() throws Exception {
        List<FilmDto> expectedResponse = Collections.singletonList(filmDtoObject());

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
        FilmDto filmToStore = filmDtoObject();
        FilmDto filmWithId = filmDtoObject();
        filmWithId.setId(UUID.randomUUID());

        when(filmService.storeFilm(filmToStore)).thenReturn(filmWithId);

        MvcResult response = mockMvc.perform(post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmToStore)))
                .andExpect(status().isOk())
                .andReturn();

        FilmDto dto = objectMapper.readValue(response.getResponse().getContentAsString(), FilmDto.class);
        assertEquals(filmToStore.getTitle(), dto.getTitle());
        assertEquals(filmToStore.getReleaseYear(), dto.getReleaseYear());
        assertEquals(filmToStore.getDirectorName(), dto.getDirectorName());
        assertNotNull(dto.getId());
    }

    @SneakyThrows
    @Test
    void testStoreDuplicateFilmThrows409() {
        FilmDto filmToStore = filmDtoObject();
        when(filmService.storeFilm(filmToStore)).thenThrow(FilmExistsException.class);

        MvcResult response = mockMvc.perform(post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmToStore)))
                .andExpect(status().isConflict())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof FilmExistsException))
                .andExpect(result -> assertNotNull(result.getResponse()))
                .andReturn();

        GenericErrorResponse errorBody = objectMapper.readValue(response.getResponse().getContentAsString(), GenericErrorResponse.class);
        assertEquals("Film exists with the given title, director and release year", errorBody.getMessage());
    }

    @Test
    void testDeleteFilm() throws Exception {
        final String idToDelete = UUID.randomUUID().toString();
        doNothing().when(filmService).removeFilmById(idToDelete);

        mockMvc.perform(delete("/films/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }

    private FilmDto filmDtoObject() {
        return FilmDto.builder()
                .directorName("John Smith")
                .title("A Long Summer Day")
                .releaseYear(1998)
                .build();
    }
}
