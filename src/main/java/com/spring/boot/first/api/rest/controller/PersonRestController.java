package com.spring.boot.first.api.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<List<Person>> readForCountryId(@PathVariable(value = "id") Long countryId){
		List<Person> peopleForCountryId = personService.buscarPersonasPorIdPais(countryId);
		if(peopleForCountryId.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(peopleForCountryId);
	}
	
	@GetMapping("country/name/{name}")
	public ResponseEntity<List<Person>> readForCountryName(@PathVariable(value = "name") String countryName){
		List<Person> peopleForCountryName = personService.buscarPersonasPorNombrePais(countryName);
		if(peopleForCountryName.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(peopleForCountryName);
	}
	
	@GetMapping("country/surname/{surname}")
	public ResponseEntity<List<Person>> readForCountrySurname(@PathVariable(value = "surname") String countrySurname){
		List<Person> peopleForCountrySurname = personService.buscarPersonasPorApellido(countrySurname);
		if(peopleForCountrySurname.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(peopleForCountrySurname);
	}
	
	@PostMapping
	public ResponseEntity<Person> create(@RequestBody Person person){
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.guardarPersona(person));	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@RequestBody Person person, @PathVariable Long id){
		Optional<Person> personUdate = personService.buscarPersonaPorId(id);
		
		if(!personUdate.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		personUdate.get().setAge(person.getAge());
		personUdate.get().setCountry(person.getCountry());
		personUdate.get().setDni(person.getDni());
		personUdate.get().setName(person.getName());
		personUdate.get().setSurname(person.getSurname());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.guardarPersona(personUdate.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Person> delete(@PathVariable Long id){
		Optional<Person> personDelete = personService.buscarPersonaPorId(id);
		if(!personDelete.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		personService.eliminarPersona(id);
		return ResponseEntity.ok().build();
	}
}