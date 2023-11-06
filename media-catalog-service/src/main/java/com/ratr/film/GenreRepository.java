package com.ratr.film;

import com.ratr.model.film.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
