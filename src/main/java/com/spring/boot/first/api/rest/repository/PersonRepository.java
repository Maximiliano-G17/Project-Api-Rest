package com.spring.boot.first.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.boot.first.api.rest.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

	List<Person> findByCountryId(Long idPais);

	List<Person> findByCountryName(String nombrePais);

	List<Person> findBySurname(String apellido);

	@Query(value = "SELECT AVG(p.age) FROM Person p WHERE p.country.name = ?1")
	Double findAverageAgeByCountryName(String countryName);

	List<Person> findTop5ByCountryNameOrderByAgeDesc(String pais);

}
