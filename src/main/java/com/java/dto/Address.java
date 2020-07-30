package com.java.dto;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Embeddable
@Data
public class Address {
	private String hno;
	private String street, city, state, country;
	int zipcode;
}
