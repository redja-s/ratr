package com.ratr.director;

import com.ratr.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface DirectorRepository extends CrudRepository<Person, Integer> {
}
