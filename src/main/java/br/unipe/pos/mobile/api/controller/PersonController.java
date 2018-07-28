package br.unipe.pos.mobile.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.unipe.pos.mobile.api.exception.PersonNotFoundException;
import br.unipe.pos.mobile.api.model.Person;
import br.unipe.pos.mobile.api.repository.PersonRepository;

import javax.validation.Valid;
import java.util.List;

/**
 */
@RestController
@RequestMapping("/api/v1.0")
public class PersonController {

	@Autowired
	PersonRepository personRepository;

	@GetMapping("/persons")
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

	@GetMapping("/persons/{id}")
	public Person getPersonById(@PathVariable(value = "id") Long id) {
		return personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException("Person", "id", id));
	}

	@PostMapping("/persons")
	public Person createPerson(@Valid @RequestBody Person person) {
		return personRepository.save(person);
	}

	@PutMapping("/persons/{id}")
	public Person updatePerson(@PathVariable(value = "id") Long id, @Valid @RequestBody Person personDetails) {

		Person person = personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException("Person", "id", id));

		person.setFirstName(personDetails.getFirstName());
		person.setLastName(personDetails.getLastName());

		Person updatedPerson = personRepository.save(person);
		return updatedPerson;
	}

	@DeleteMapping("/persons/{id}")
	public ResponseEntity<Person> deletePerson(@PathVariable(value = "id") Long id) {

		Person person = personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException("Person", "id", id));

		personRepository.delete(person);
		return ResponseEntity.ok().build();
	}
}
