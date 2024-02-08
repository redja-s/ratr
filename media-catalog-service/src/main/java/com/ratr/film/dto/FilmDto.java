package com.ratr.film.dto;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class FilmDto {

    private UUID id;

    @NotNull(message = "Name cannot be null")
    private String title;

    @Min(value = 1900, message = "releaseYear cannot be before 1900")
    private int releaseYear;
    private String directorName;
    private String description;
    private String coverImagePath;
}
