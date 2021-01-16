package com.spring.boot.first.api.rest.service;

import java.util.List;
import java.util.Optional;

import com.spring.boot.first.api.rest.domain.Person;

public interface PersonService {

	List<Person> buscarTodos();

	Person guardarPersona(Person personaAGuardar);

	Optional<Person> buscarPersonaPorId(Long id);

	List<Person> buscarPersonasPorIdPais(Long idPais);

	List<Person> buscarPersonasPorNombrePais(String nombrePais);

	List<Person> buscarPersonasPorApellido(String apellido);


}
