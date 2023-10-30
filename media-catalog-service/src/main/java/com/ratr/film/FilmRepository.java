package com.ratr.film;

import com.ratr.model.Film;
import jakarta.persistence.Entity;
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
}
