package com.spring.boot.first.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.first.api.rest.domain.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

	Optional<Country> findByName(String CountryName);

}
