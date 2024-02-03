package com.ratr.film.service;

import com.ratr.film.FilmRepository;
import com.ratr.film.dto.FilmDto;
import com.ratr.film.exception.FilmExistsException;
import com.ratr.film.mapper.EntityMapper;
import com.ratr.model.film.Film;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FilmService {

    private final FilmRepository filmRepository;
    private final EntityMapper entityMapper = EntityMapper.INSTANCE;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<FilmDto> getAllFilms() {
        List<Film> allFilms = filmRepository.findAll();
        return allFilms.stream().map(film -> FilmDto.builder()
                .releaseYear(film.getReleaseYear())
                .title(film.getTitle())
                .directorName(film.getDirectorName())
                .build()).collect(Collectors.toList());
    }

    public List<FilmDto> getFilmByTitle(String filmTitle) {
        List<Film> films = filmRepository.findFilmsByTitle(filmTitle);
        log.info(films.toString());
        return films.stream().map(entityMapper::mapEntityToDto).toList();
    }

    public FilmDto storeFilm(FilmDto filmDto) throws FilmExistsException {
        String checkExistence = filmRepository.getFilmIdByTitleDirectorAndReleaseYear(
                filmDto.getTitle(),
                filmDto.getDirectorName(),
                filmDto.getReleaseYear());

        log.info("existence is " + checkExistence);

        if (!Objects.equals(checkExistence, null)) {
            throw new FilmExistsException("This film already exists");
        }

        Film savedFilm = filmRepository.save(entityMapper.mapDtoToEntity(filmDto));

        return entityMapper.mapEntityToDto(savedFilm);
    }
}
