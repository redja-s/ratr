package com.ratr.mapper;

import com.ratr.film.dto.FilmDto;
import com.ratr.film.mapper.EntityMapper;
import com.ratr.model.film.Film;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
public class EntityMapperTests {

    private final EntityMapper entityMapper = EntityMapper.INSTANCE;

    @Test
    void testFilmMapsToFilmDto() {
        Film filmToConvert = Film.builder()
                .releaseYear(1995)
                .directorName("John Doe")
                .title("Sunbeam")
                .id(UUID.randomUUID())
                .description("A description of the film")
                .coverImagePath("sunbeam.png")
                .build();

        FilmDto mapped = entityMapper.mapEntityToDto(filmToConvert);

        assertEquals(filmToConvert.getReleaseYear(), mapped.getReleaseYear());
        assertEquals(filmToConvert.getTitle(), mapped.getTitle());
        assertEquals(filmToConvert.getDirectorName(), mapped.getDirectorName());
        assertEquals(filmToConvert.getDescription(), mapped.getDescription());
    }

    @Test
    void testFilmDtoMapsToFilm() {
        FilmDto filmToConvert = FilmDto.builder()
                .releaseYear(1995)
                .directorName("John Doe")
                .description("A description of the film")
                .id(UUID.randomUUID())
                .title("Sunbeam")
                .build();

        Film mapped = entityMapper.mapDtoToEntity(filmToConvert);

        assertEquals(filmToConvert.getReleaseYear(), mapped.getReleaseYear());
        assertEquals(filmToConvert.getTitle(), mapped.getTitle());
        assertEquals(filmToConvert.getDirectorName(), mapped.getDirectorName());
        assertEquals(filmToConvert.getDescription(), mapped.getDescription());
        assertEquals(filmToConvert.getId(), mapped.getId());
    }
}
