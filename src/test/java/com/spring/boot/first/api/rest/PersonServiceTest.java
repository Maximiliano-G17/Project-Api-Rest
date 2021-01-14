package com.spring.boot.first.api.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.spring.boot.first.api.rest.domain.Person;
import com.spring.boot.first.api.rest.service.PersonService;

@SpringBootTest
public class PersonServiceTest {
	
	private PersonService personService;
		
	@Autowired
	public PersonServiceTest(PersonService personService) {
		this.personService = personService;
	}
	
	@Test
	public void buscarTodasLasPersonas_retornaListaDePersonas() {
		List<Person> listaDePersonas = personService.buscarTodos();
		
		assertThat(listaDePersonas).isNotNull();
		assertEquals(1,listaDePersonas.size());
	}
	
	@Test
	public void buscarPersonaPorId_conIdExistente_retornaUnaPersona() {
		Long id = 1L;
		
		Optional<Person> personaBuscada = personService.buscarPersonaPorId(id);
		
		if(personaBuscada.isPresent()) {
			assertThat(personaBuscada).isNotNull();
			assertEquals(1L,personaBuscada.get().getId());
		}else {
			System.out.println("ERROR");
		}
	}
	
	@Test
	public void buscarPersonaPorId_conIdInexistente_retornaStringDeError() {
		Long id = -1000L;
		
		Optional<Person> personaBuscada = personService.buscarPersonaPorId(id);
		
		if(personaBuscada.isPresent()) {
			assertThat(personaBuscada).isNotNull();
			assertEquals(1L,personaBuscada.get().getId());
		}else {
			System.out.println("------ PERSONA NO ENCONTRADA ------");
		}
	}
	
	@Test
	public void buscarPersonaPorIdPais_conIdExistente_retornaListaDePersonas() {
		Long idPais = 1L;
		
		List<Person> personasEncontradasPorPais = personService.buscarPersonasPorIdPais(idPais);
		
		assertThat(personasEncontradasPorPais).isNotNull();
		assertEquals("Argentina",personasEncontradasPorPais.get(0).getCountry().getName());
	}
	
	@Test
	public void buscarPersonaPorIdPais_conIdInexistente_retornaNull() {
		Long idPais = -100L;
		
		List<Person> personasEncontradasPorPais = personService.buscarPersonasPorIdPais(idPais);
		
		assertThat(personasEncontradasPorPais).isEmpty();
	}
	
//	@Test
//	public void guardarPersona() {
//		
//		Person personaAGuardar = new Person();
//		personaAGuardar.setName("Fernando");
//		personaAGuardar.setSurname("Lopez");
//		personaAGuardar.setAge("30");
//		personaAGuardar.setDni("25.321.123");
//		
//		Person personaGuardada = personService.guardarPersona(personaAGuardar);
//		
//		assertEquals(personaAGuardar.getName(), personaGuardada.getName());
//	}

}
