package com.ratr.model.film;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "films")
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title")
    @JsonProperty("title")
    @NotNull
    private String title;

    @Column(name = "director_name")
    @JsonProperty("director_name")
    @NotNull
    private String directorName;

    @Column(name = "release_year")
    @JsonProperty("release_year")
    @NotNull
    private int releaseYear;

    @NotNull
    private String description;

    @JsonProperty("cover_image_path")
    @Column(name = "cover_image_path")
    private String coverImagePath;

}
