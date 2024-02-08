package com.ratr.film.service;

import com.ratr.film.FilmRepository;
import com.ratr.film.dto.FilmDto;
import com.ratr.film.exception.FilmExistsException;
import com.ratr.film.mapper.EntityMapper;
import com.ratr.model.film.Film;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        return allFilms.stream().map(entityMapper::mapEntityToDto).toList();
    }

    public List<FilmDto> getFilmByTitle(String filmTitle) {
        List<Film> films = filmRepository.findFilmsByTitle(filmTitle);
        return films.stream().map(entityMapper::mapEntityToDto).toList();
    }

    public FilmDto storeFilm(FilmDto filmDto) throws FilmExistsException {
        try {
            Film savedFilm = filmRepository.saveAndFlush(entityMapper.mapDtoToEntity(filmDto));
            log.info("returned " + savedFilm);
            return entityMapper.mapEntityToDto(savedFilm);
        } catch (DataIntegrityViolationException e) {
            throw new FilmExistsException();
        }
    }

    public void removeFilmById(String filmId) {
        filmRepository.deleteById(UUID.fromString(filmId));
    }
}
