package com.ratr.film.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmResponse implements Serializable {
    private String filmTitle;
    private String directorName;
    private String description;
    private int releaseYear;
}
