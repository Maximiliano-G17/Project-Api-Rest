package com.spring.boot.first.api.rest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String population;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", population=" + population + "]";
	}
}