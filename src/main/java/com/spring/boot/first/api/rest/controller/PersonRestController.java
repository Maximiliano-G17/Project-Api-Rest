package com.spring.boot.first.api.rest.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
			return ResponseEntity.notFound()
								 .build();
		}
		return ResponseEntity.ok(person);
	}
	
	@GetMapping
	public ResponseEntity<List<Person>> readAll(){
		List<Person> people = personService.buscarTodos();
		return ResponseEntity.ok(people);
	}
	
	@GetMapping("top5/{countryName}")
	public ResponseEntity<List<Person>> readTopFive(@PathVariable String countryName){
		List<Person> topFive = personService.buscarTopFive(countryName);
		if(!topFive.isEmpty()) {
			return ResponseEntity.ok(topFive);
			
		}
		return ResponseEntity.notFound()
							 .build();
	}
	
	@GetMapping("average/{countryName}")
	public ResponseEntity<Double> readByAverageCountry(@PathVariable String countryName){
		Double average = personService.calcularPromedio(countryName);
		if(average != null) {
			return ResponseEntity.ok(average);
			
		}
		return ResponseEntity.notFound()
							 .build();
	}
	
	@GetMapping("country/{id}")
	public ResponseEntity<List<Person>> readByCountryId(@PathVariable(value = "id") Long countryId){
		List<Person> peopleForCountryId = personService.buscarPersonasPorIdPais(countryId);
		if(peopleForCountryId.isEmpty()) {
			return ResponseEntity.notFound()
								 .build();
		}
		return ResponseEntity.ok(peopleForCountryId);
	}
	
	@GetMapping("country/name/{name}")
	public ResponseEntity<List<Person>> readByCountryName(@PathVariable(value = "name") String countryName){
		List<Person> peopleForCountryName = personService.buscarPersonasPorNombrePais(countryName);
		if(peopleForCountryName.isEmpty()) {
			return ResponseEntity.notFound()
								 .build();
		}
		return ResponseEntity.ok(peopleForCountryName);
	}
	
	@GetMapping("surname/{surname}")
	public ResponseEntity<List<Person>> readBySurname(@PathVariable String surname){
		List<Person> peopleBySurname = personService.buscarPersonasPorApellido(surname);
		if(peopleBySurname.isEmpty()) {
			return ResponseEntity.notFound()
								 .build();
		}
		return ResponseEntity.ok(peopleBySurname);
	}
	
	@PostMapping
	public ResponseEntity<Person> create(@Valid @RequestBody Person person,BindingResult result){
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error: " + seeErrors(result));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.guardarPersona(person));	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@RequestBody Person persona, @PathVariable Long id){
		Optional<Person> personUdate = personService.buscarPersonaPorId(id);
		
		if(!personUdate.isPresent()) {
			return ResponseEntity.notFound()
								 .build();
		}		
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(personService.actualizarPersona(personUdate,persona));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Person> delete(@PathVariable Long id){
		Optional<Person> personDelete = personService.buscarPersonaPorId(id);
		if(!personDelete.isPresent()) {
			return ResponseEntity.notFound()
								 .build();
		}
		personService.eliminarPersona(id);
		return ResponseEntity.ok()
							 .build();
	}
	
	private List<String> seeErrors(BindingResult result) {		
		List<String> errors = result.getAllErrors()
									.stream()
									.map(error -> error.getDefaultMessage())
									.collect(Collectors.toList());
		return errors;
	}
}