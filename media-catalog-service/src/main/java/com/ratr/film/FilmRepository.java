package com.ratr.film;

import com.ratr.model.film.Film;
import com.ratr.model.film.GenreEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
public interface FilmRepository extends JpaRepository<Film, UUID> {

    @Query("SELECT f.title FROM Film f WHERE f.directorName = :dname")
    List<String> findByDirectorName(@Param("dname") String directorName);

    @Query("SELECT g.genre FROM Film f INNER JOIN FilmGenre fg on f.id = fg.filmId INNER JOIN Genre g on fg.genreId = g.id WHERE f.id = :fid ")
    List<GenreEnum> findGenresForFilmById(@Param("fid") String filmId);

    @Query("SELECT f.id FROM films WHERE f.title = :ftitle AND f.directorName = :fdirector AND f.releaseYear = :freleaseyear")
    String getFilmIdByTitleDirectorAndReleaseYear(
            @Param("ftitle") String title,
            @Param("fdirector") String directorName,
            @Param("freleaseyear") int releaseYear
    );
}
