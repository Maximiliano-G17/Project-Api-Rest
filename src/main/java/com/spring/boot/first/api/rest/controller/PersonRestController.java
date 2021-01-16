package com.spring.boot.first.api.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.first.api.rest.domain.Person;
import com.spring.boot.first.api.rest.service.PersonService;

@RestController
@RequestMapping("/api/people")
public class PersonRestController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Person>> read(@PathVariable(value = "id") Long personId){
		Optional<Person> person = personService.buscarPersonaPorId(personId);
		if(!person.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(person);
	}
	
	@GetMapping
	public ResponseEntity<List<Person>> readAll(){
		List<Person> people = personService.buscarTodos();
		return ResponseEntity.ok(people);
	}
	
	@GetMapping("country/{id}")
	public ResponseEntity<List<Person>> readForCountry(@PathVariable(value = "id") Long countryId){
		List<Person> peopleForCountryId = personService.buscarPersonasPorIdPais(countryId);
		if(peopleForCountryId.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(peopleForCountryId);
	}
	
	@PostMapping
	public ResponseEntity<Person> create(@RequestBody Person person){
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.guardarPersona(person));	
	}

}
