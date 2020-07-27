package com.java.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Appointment {
	@Id
	@GeneratedValue
	private int id;
	private int doctorId;
	private int patientId;
	private String time;
	private Integer rate;
}
