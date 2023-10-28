package com.ratr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    @Column(name = "director_name")
    @JsonProperty("director_name")
    @NotNull
    private String directorName;

    @Column(name = "release_year")
    @JsonProperty("release_year")
    @NotNull
    private int releaseDate;
}
