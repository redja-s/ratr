package com.js.ratr.people;

import com.js.lib.model.Person;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Data
public class PeopleController {
    private final PeopleRepository peopleRepository;

    @GetMapping("/people")
    public List<Person> getAllPeople() {
        return peopleRepository.findAll();
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable UUID id) {
        Optional<Person> person = peopleRepository.findById(id);
        return person.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        String name = person.getName();
        LocalDate dob = person.getDateOfBirth();
        Optional<Person> personExists = peopleRepository.findByNameAndDateOfBirth(name, dob);

        if (personExists.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Person savedPerson = peopleRepository.save(person);
        return ResponseEntity.ok(savedPerson);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable UUID id, @RequestBody Person updatedPerson) {
        Optional<Person> person = peopleRepository.findById(id);
        if (person.isPresent()) {
            updatedPerson.setId(id);
            Person savedPerson = peopleRepository.save(updatedPerson);
            return ResponseEntity.ok(savedPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        Optional<Person> person = peopleRepository.findById(id);
        if (person.isPresent()) {
            peopleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
