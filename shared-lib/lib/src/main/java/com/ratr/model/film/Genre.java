package com.ratr.model.film;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;

@Entity
@Table(name = "genres", uniqueConstraints = @UniqueConstraint(columnNames = "genre"))
@Builder
public class Genre {
    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private GenreEnum genre;
}
