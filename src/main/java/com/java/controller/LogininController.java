package com.java.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dao.DoctorRepository;
import com.java.dao.PatientRepository;
import com.java.dto.Doctor;
import com.java.dto.Patient;

@Controller
@RequestMapping("/login")
public class LogininController {
	@Autowired
	PatientRepository pr;
	@Autowired
	DoctorRepository dr;

	@GetMapping("/patient")
	public String getPatient() {
		return "patientLogin";
	}

	@PostMapping("/patient")
	public String getPatient(HttpServletRequest req, @RequestParam("username") String username,
			@RequestParam("password") String password, Model model) {
		Optional<Patient> op = pr.findByUsernameAndPassword(username, password);
		if (op.isPresent()) {
			// success login add patient to session
			req.getSession().setAttribute("patient", op.get());
			return "redirect:/";
		} else {
			model.addAttribute("massage", "Invalid username or password, try again.");
			return "patientLogin";
		}
	}

	@GetMapping("/doctor")
	public String getDoctor() {
		return "doctorLogin";
	}

	@PostMapping("/doctor")
	public String getDoctor(HttpServletRequest req, @RequestParam("username") String username,
			@RequestParam("password") String password, Model model) {
		Optional<Doctor> op = dr.findByUsernameAndPassword(username, password);
		if (op.isPresent()) {
			// success login add patient to session
			req.getSession().setAttribute("doctor", op.get());
			return "redirect:/";
		} else {
			model.addAttribute("massage", "Invalid username or password, try again.");
			return "doctorLogin";
		}
	}
}
