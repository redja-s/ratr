package com.ratr.model.film;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;

import java.util.UUID;

@Entity
@Table(name = "films_genres")
@Builder
public class FilmGenre {

    @Id
    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private UUID filmId;

    @Id
    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private int genreId;
}
