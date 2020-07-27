package com.java.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Doctor {
	@Id
	@GeneratedValue
	private int id;
	@Column(unique = true)
	private String username;
	private String password, name;
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> qualifications;
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> speciality;
	private int yearsOfExp;
	private boolean verified;
	private String description;
	@Embedded
	private Address address;// aggregation; composition

//value objects do not have their own primary key
	@Data
	@Embeddable
	static public class Address {
		private String hno;
		private String street, city, state, country;
		int zipcode;
	}

}