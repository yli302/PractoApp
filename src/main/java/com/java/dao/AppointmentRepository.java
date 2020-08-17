package com.java.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.Appointment;
import com.java.dto.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	
	public List<Appointment> findByPatient(Patient p);

}
