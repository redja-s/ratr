package com.js.ratr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "films")
@IdClass(FilmPrimaryKey.class)
@Getter
@Setter
@NoArgsConstructor
public class Film {

    @Id
    @NotEmpty
    private String title;

    @Id
    @NotEmpty
    private String director;

    @Id
    @JsonProperty("uk_release_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate ukReleaseDate;

    private String genre;

    @Min(0)
    @Max(10)
    private Double rating;

    private String review;


    public Film(String title, String director, LocalDate ukReleaseDate, Double rating, String review) {
        this.title = title;
        this.director = director;
        this.ukReleaseDate = ukReleaseDate;
        this.rating = rating;
        this.review = review;
    }
}
