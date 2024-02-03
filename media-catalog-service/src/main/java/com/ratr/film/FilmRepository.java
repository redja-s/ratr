package com.ratr.film;

import com.ratr.model.film.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
public interface FilmRepository extends JpaRepository<Film, UUID> {

  @Query("SELECT f FROM Film f WHERE f.directorName = :dname")
  List<Film> findByDirectorName(@Param("dname") String directorName);

  @Query("SELECT f FROM Film f WHERE f.title = :ftitle")
  List<Film> findFilmsByTitle(@Param("ftitle") String filmTitle);
  @Query("SELECT f.id FROM Film f WHERE f.title = :ftitle AND f.directorName = :fdirector AND f.releaseYear = :freleaseyear")
  String getFilmIdByTitleDirectorAndReleaseYear(
      @Param("ftitle") String title,
      @Param("fdirector") String directorName,
      @Param("freleaseyear") int releaseYear);
}
