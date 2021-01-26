package com.spring.boot.first.api.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.first.api.rest.domain.Person;
import com.spring.boot.first.api.rest.repository.PersonRepository;

@Service
@Transactional
public class PersonServiceImpl implements PersonService{
	
	private PersonRepository personRepo;
	
	public PersonServiceImpl(PersonRepository personRepo) {
		this.personRepo = personRepo;
	}

	@Override
	public List<Person> buscarTodos() {
		return personRepo.findAll();
	}

	@Override
	public Person guardarPersona(Person personaAGuardar) {	
		return personRepo.save(personaAGuardar);
	}

	@Override
	public Optional<Person> buscarPersonaPorId(Long id) {
		return personRepo.findById(id);
	}

	@Override
	public List<Person> buscarPersonasPorIdPais(Long idPais) {
		return personRepo.findByCountryId(idPais);
	}

	@Override
	public List<Person> buscarPersonasPorNombrePais(String nombrePais) {
		return personRepo.findByCountryName(nombrePais);
	}

	@Override
	public List<Person> buscarPersonasPorApellido(String apellido) {
		return personRepo.findBySurname(apellido);
	}

	@Override
	public void eliminarPersona(Long id) {
		personRepo.deleteById(id);		
	}

	@Override
	public Person actualizarPersona(Optional<Person> personaAActualizar,Person personaActualizada) {
		personaAActualizar.get().setAge(personaActualizada.getAge());
		personaAActualizar.get().setCountry(personaActualizada.getCountry());
		personaAActualizar.get().setDni(personaActualizada.getDni());
		personaAActualizar.get().setName(personaActualizada.getName());
		personaAActualizar.get().setSurname(personaActualizada.getSurname());	
		
		return personRepo.save(personaAActualizar.get());
	}

	@Override
	public Double calcularPromedio(String pais) {
		return personRepo.findAverageAgeByCountryName(pais);
	}

	@Override
	public List<Person> buscarTopFive(String pais) {
		return personRepo.findTop5ByCountryNameOrderByAgeDesc(pais);
	}
}