package com.spring.boot.first.api.rest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "people")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "The name cannot be empty")
	@Min(2)
	private String name;
	@NotEmpty(message = "The surname cannot be empty")
	@Min(2)
	private String surname;
	@NotEmpty(message = "The DNI cannot be empty")
	@Pattern(regexp = "[0-9]{8}", message = "The DNI must contain only numbers, a length of 8 and without points")
	private String dni;
	@NotEmpty(message = "The age cannot be empty")
	@Pattern(regexp = "[0-9]{2}", message = "The age must contain only numbers")
	private String age;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_country")
	private Country country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", surname=" + surname + ", dni=" + dni + ", age=" + age
				+ ", country=" + country + "]";
	}
}