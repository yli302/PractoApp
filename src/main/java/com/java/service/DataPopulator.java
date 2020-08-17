package com.java.service;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.java.dao.DoctorRepository;
import com.java.dao.UserRepository;
import com.java.dto.Doctor;
import com.java.dto.User;

@Component("dp")
@Profile("dev") // spring.profiles.active=dev @Profile("!prod")
public class DataPopulator {

	@Autowired
	UserRepository rep;
	@Autowired
	DoctorRepository doc;

	@PostConstruct
	@Transactional
	public void init() {
		System.out.println("************************* init called");
		User u1 = new User("payal", "$2a$10$AISLUkhiXJU2sEUlZver2Om.CpOsVzEjYTK0mv7lH5kIfVpjM2Pne",
				Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
		rep.save(u1);
		User u2 = new User("shikha", "$2a$10$FnElsq5mDQs5yyjYnsAE/./rdWMz9wHKjyKlgKvSVclkcaq6vEvWq",
				Arrays.asList("ROLE_USER"));
		rep.save(u2);
		User u3 = new User("patient", "$2a$10$qaBv5pqbqyO9Aj/PEUkZCO7NoesnIUsEKKKz.25fab6UhQzyEmk0.",
				Arrays.asList("ROLE_PATIENT"));
		rep.save(u3);
		User u4 = new User("doctor", "$2a$10$lYNm/cGyxVYyz5kzcvnrsOsFwvGY5ljkrR5nJc692UO5Fin2UWaF6",
				Arrays.asList("ROLE_DOCTOR"));
		rep.save(u4);
		Doctor doc = new Doctor();
	}

}
