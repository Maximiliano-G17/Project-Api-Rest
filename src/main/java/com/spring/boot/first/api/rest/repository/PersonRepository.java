package com.spring.boot.first.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.first.api.rest.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
