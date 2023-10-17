package com.js.ratr.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class FilmPrimaryKey implements Serializable {

    private String title;
    private LocalDate ukReleaseDate;
    private String director;

    public FilmPrimaryKey(String title, LocalDate ukReleaseDate, String director) {
        this.title = title;
        this.director = director;
        this.ukReleaseDate = ukReleaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmPrimaryKey that = (FilmPrimaryKey) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(director, that.director) &&
                Objects.equals(ukReleaseDate, that.ukReleaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, director, ukReleaseDate);
    }
}
