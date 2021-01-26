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
		}
	}
	
	@Test
	public void buscarPersonaPorId_conIdInexistente_retornaStringDeError() {
		Long id = -1000L;
		
		Optional<Person> personaBuscada = personService.buscarPersonaPorId(id);
		
		if(!personaBuscada.isPresent()) {
			assertThat(personaBuscada).isNull();
		}
	}
	
	@Test
	public void buscarPersonasPorIdPais_conIdExistente_retornaListaDePersonas() {
		Long idPais = 1L;
		
		List<Person> personasEncontradasPorPais = personService.buscarPersonasPorIdPais(idPais);
		
		assertThat(personasEncontradasPorPais).isNotNull();
		assertEquals(idPais,personasEncontradasPorPais.get(0).getCountry().getId());
	}
	
	@Test
	public void buscarPersonasPorIdPais_conIdInexistente_retornaNull() {
		Long idPais = -100L;
		
		List<Person> personasEncontradasPorPais = personService.buscarPersonasPorIdPais(idPais);
		
		assertThat(personasEncontradasPorPais).isEmpty();
	}
	
	@Test
	public void buscarPersonasPorNombrePais_conNombreExistente_retornaListaDePersonas() {
		String nombrePais = "Argentina";
		
		List<Person> personasEncontradasPorNombrePais = personService.buscarPersonasPorNombrePais(nombrePais);
		
		assertThat(personasEncontradasPorNombrePais).isNotNull();
		assertEquals("Argentina",personasEncontradasPorNombrePais.get(0).getCountry().getName());
	}
	
	@Test
	public void buscarPersonasPorNombrePais_conNombreInexistente_retornaListaVacia() {
		String nombrePais = "ZZZZZZZ";
		
		List<Person> personasEncontradasPorNombrePais = personService.buscarPersonasPorNombrePais(nombrePais);
		
		assertThat(personasEncontradasPorNombrePais).isEmpty();
	}
	
	@Test
	public void buscarPersonasPorApellido_conApellidoExistente_retornaUnaListaPersona() {
		String apellido = "Lopez";
		
		List<Person> personasEncontradas = personService.buscarPersonasPorApellido(apellido);
		
		assertThat(personasEncontradas).isNotNull();
		assertEquals("Lopez",personasEncontradas.get(0).getSurname());
	}
	
	@Test
	public void buscarPersonasPorApellido_conApellidoInexistente_retornaUnaListaVacia() {
		String apellido = "ZZZZZZZ";
		
		List<Person> personasEncontradas = personService.buscarPersonasPorApellido(apellido);
		
		assertThat(personasEncontradas).isEmpty();
	}
	
	@Test
	public void buscarPromedioDeEdadPorPais_conNombreDePaisExistente_retornaPromedioDeEdad() {
		String pais = "Argentina";
		
		List<Person> people = personService.buscarPersonasPorNombrePais(pais);
		//obtengo el promedio de edad del Pais (Ej: Argentina)
		Double average = people.stream().mapToInt(p -> Integer.valueOf(p.getAge())).average().orElse(0);
		//para despues comprarla con el promedio del metodo calcularPromedio que recibe un nombre de pais(Ej: Argentina)
		//y asi ver si coinciden es que esta todo bien.
		Double promedioPorPais = personService.calcularPromedio(pais);
		
		assertThat(promedioPorPais).isNotNull();
		assertEquals(average,promedioPorPais);
	}
	
	@Test
	public void buscarPromedioDeEdadPorPais_conNombreDePaisInexistente_retornaNull() {
		String pais = "ZZZZZZZZZZZ";
		
		Double promedioPorPais = personService.calcularPromedio(pais);
		
		assertThat(promedioPorPais).isNull();
	}
	
	@Test
	public void buscarTop5DePersonasMayoresSegunElPais_conNombreDePaisExistente_retornaListaDePersonas() {
		String pais = "Argentina";
		
		List<Person> topFiveThePeople = personService.buscarTopFive(pais);
		
		assertThat(topFiveThePeople).isNotEmpty();
		assertEquals(5,topFiveThePeople.size());		
	}
	
	@Test
	public void buscarTop5DePersonasMayoresSegunElPais_conNombreDePaisInexistente_retornaListaVacia() {
		String pais = "ZZZZZZZZZZZ";
		
		List<Person> topFiveThePeopleEmpty = personService.buscarTopFive(pais);
		
		assertThat(topFiveThePeopleEmpty).isEmpty();	
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
