package com.spring.boot.first.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.first.api.rest.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

	List<Person> findByCountryId(Long idPais);

}
