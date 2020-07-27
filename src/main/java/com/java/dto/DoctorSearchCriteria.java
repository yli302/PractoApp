package com.java.dto;

import lombok.Data;

@Data
public class DoctorSearchCriteria {
	private String name;
	private String city;
	private String speciality;
}