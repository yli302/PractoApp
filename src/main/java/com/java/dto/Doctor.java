package com.java.dto;

import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, Boolean> schedule;
}