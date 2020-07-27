package com.java.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
public class Patient {
	@Id
	@GeneratedValue
	private int id;
	@Column(unique = true)
	@NotEmpty(message = "Please provide a name")
	@Size(min = 3, max = 10, message = "Username is 3~10, type again")
	private String username;
	@NotEmpty(message = "Please provide a password")
	@Size(min = 3, max = 10, message = "Password is 3~10, type again")
	private String password;
	@NotEmpty(message = "Please provide a phone number for doctor contacting")
	@Size(min = 10, max = 10, message = "Phone should be 10 digits number, type again")
	private String phone;
	@Column(unique = true)
	@NotEmpty(message = "Please provide a email for doctor contacting")
	private String email;
}
