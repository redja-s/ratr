package com.ratr.film.mapper;

import com.ratr.film.dto.FilmDto;
import com.ratr.model.film.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    @Mapping(target = "id", ignore = true)
    Film mapDtoToEntity(FilmDto filmDto);

    FilmDto mapEntityToDto(Film film);
}
