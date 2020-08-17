package com.java.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{
	
		public Optional<Patient> findByUsernameAndPassword(String username, String password);
}
