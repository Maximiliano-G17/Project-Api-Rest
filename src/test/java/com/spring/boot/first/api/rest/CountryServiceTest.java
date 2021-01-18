package com.spring.boot.first.api.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.boot.first.api.rest.domain.Country;
import com.spring.boot.first.api.rest.service.CountryService;

@SpringBootTest
public class CountryServiceTest {
	
	private CountryService countryService;

	@Autowired
	public CountryServiceTest(CountryService countryService) {
		this.countryService = countryService;
	}

	@Test
	public void buscarPorId_conIdExistente_retornaUnPais() {
		Long idPais = 1L;
		
		Optional<Country> paisEncontrado = countryService.buscarPorId(idPais);
		
		assertThat(paisEncontrado).isNotNull();
		assertEquals(idPais,paisEncontrado.get().getId());
	}
	
	@Test
	public void buscarPorId_conIdInexistente_retornaStringError() {
		Long idPais = -100L;
		
		Optional<Country> paisEncontrado = countryService.buscarPorId(idPais);
		
		if(!paisEncontrado.isPresent()) {
			System.out.println("------ Pais NO ENCONTRADO ------");
		}
	}
	
	@Test
	public void buscarPorNombre_conNombreExistente_retornaUnPais() {
		String nombrePais = "Argentina";
		
		Optional<Country> paisEncontrado = countryService.buscarPorNombre(nombrePais);
		
		assertThat(paisEncontrado).isNotNull();
		assertEquals(nombrePais,paisEncontrado.get().getName());
	}
	
	@Test
	public void buscarPorNombre_conNombreInexistente_retornaStringError() {
		String nombrePais = "ZZZZZZZZZ";
		
		Optional<Country> paisEncontrado = countryService.buscarPorNombre(nombrePais);
		
		if(!paisEncontrado.isPresent()) {
			System.out.println("------ Pais NO ENCONTRADO ------");
		}
	}
	
	@Test
	public void buscarTodosLosPaises_retornaUnaListaPais() {
		List<Country> paises = countryService.buscarTodosLosPaises();
		
		assertThat(paises).isNotEmpty();
		assertEquals(6, paises.size());
	}
}
