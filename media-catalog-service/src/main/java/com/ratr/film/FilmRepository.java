package com.ratr.film;

import com.ratr.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface FilmRepository extends JpaRepository<Film, UUID> {
}
