package com.ratr.people;

import com.ratr.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Transactional
public interface PeopleRepository extends JpaRepository<Person, UUID> {
    Optional<Person> findByNameAndDateOfBirth(String name, LocalDate dateOfBirth);
}
