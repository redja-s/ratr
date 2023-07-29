package com.js.ratr.director;

import com.js.ratr.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface DirectorRepository extends CrudRepository<Person, Integer> {
}
