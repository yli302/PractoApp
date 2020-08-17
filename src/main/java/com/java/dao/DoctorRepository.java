package com.java.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
	public List<Doctor> findByNameAndAddress_CityAndSpeciality(String name, String city, String speciality);
	public Optional<Doctor> findByUsernameAndPassword(String username, String password);
}
