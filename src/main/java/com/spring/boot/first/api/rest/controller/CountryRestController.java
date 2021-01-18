package com.spring.boot.first.api.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.first.api.rest.domain.Country;
import com.spring.boot.first.api.rest.service.CountryService;

@RestController
@RequestMapping("api/country")
public class CountryRestController {
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Country>> read(@PathVariable Long id){
		Optional<Country> countryFound = countryService.buscarPorId(id);
		if(!countryFound.isPresent()) {
			return ResponseEntity.notFound()
								 .build();
		}
		return ResponseEntity.ok(countryFound);
	}
	
	@GetMapping
	public ResponseEntity<List<Country>> readAll(){
		List<Country> countries = countryService.buscarTodosLosPaises();
		return ResponseEntity.ok(countries);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Optional<Country>> readForName(@PathVariable String name){
		Optional<Country> countryFoundForName = countryService.buscarPorNombre(name);
		if(!countryFoundForName.isPresent()) {
			return ResponseEntity.notFound()
								 .build();
		}
		return ResponseEntity.ok(countryFoundForName);
	}
}