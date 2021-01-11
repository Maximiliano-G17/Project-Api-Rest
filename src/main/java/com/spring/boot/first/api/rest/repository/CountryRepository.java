package com.spring.boot.first.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.first.api.rest.domain.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

}
