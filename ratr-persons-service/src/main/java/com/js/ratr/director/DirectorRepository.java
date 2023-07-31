package com.js.ratr.people;

import com.js.ratr.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface DirectorRepository extends CrudRepository<Person, Integer> {
}
