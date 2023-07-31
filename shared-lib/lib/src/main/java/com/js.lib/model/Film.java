package com.js.lib.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Person director;

    @Column(name = "director_name")
    @JsonProperty("director_name")
    private String directorName;

    @Column(name = "release_date")
    @JsonProperty("release_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull
    private LocalDate releaseDate;
}
