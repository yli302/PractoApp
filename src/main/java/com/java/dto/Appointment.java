package com.java.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Appointment {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(cascade = CascadeType.ALL)
	private Doctor doctor;
	@ManyToOne(cascade = CascadeType.ALL)
	private Patient patient;
	private String time;
	private Integer rate;
}
