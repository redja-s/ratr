package com.ratr.model.film;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@AllArgsConstructor
@Builder
public class Film {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @Column(name = "title")
    @NotBlank(message = "Film title is mandatory")
    private String title;

    @Column(name = "director_name")
    @NotBlank(message = "Director name is mandatory")
    private String directorName;

    @Column(name = "release_year")
    @NotNull(message = "Release year is mandatory")
    private int releaseYear;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @Column(name = "cover_image_path")
    private String coverImagePath;
}
