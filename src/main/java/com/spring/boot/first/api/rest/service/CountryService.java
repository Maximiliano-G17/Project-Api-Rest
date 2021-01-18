package com.spring.boot.first.api.rest.service;

import java.util.List;
import java.util.Optional;

import com.spring.boot.first.api.rest.domain.Country;

public interface CountryService {

	Optional<Country> buscarPorId(Long idPais);

	List<Country> buscarTodosLosPaises();

	Optional<Country> buscarPorNombre(String nombrePais);

}
