package com.js.lib.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "people", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "date_of_birth"}))
@NoArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue
    private UUID id;

    @NotEmpty
    private String name;

    @Column(name = "date_of_birth")
    @JsonProperty("date_of_birth")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull
    private LocalDate dateOfBirth;
}
