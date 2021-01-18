package com.spring.boot.first.api.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.first.api.rest.domain.Country;
import com.spring.boot.first.api.rest.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService{
	
	@Autowired
	private CountryRepository countryRepo;

	@Override
	public Optional<Country> buscarPorId(Long idPais) {
		return countryRepo.findById(idPais);
	}

	@Override
	public List<Country> buscarTodosLosPaises() {
		return countryRepo.findAll();
	}

	@Override
	public Optional<Country> buscarPorNombre(String nombrePais) {
		return countryRepo.findByName(nombrePais);
	}

}
