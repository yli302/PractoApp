package com.java.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dao.DoctorRepository;
import com.java.dao.PatientRepository;
import com.java.dto.Doctor;
import com.java.dto.Doctor.Address;
import com.java.dto.Patient;

@Controller
@RequestMapping("/signup")
public class SignupController {
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/patient")
	public String getSignPatient() {
		return "patientSignup";
	}

	@PostMapping("/patient")
	public String getSignPatient(HttpServletRequest req, @Valid @ModelAttribute Patient patient, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			// validation fail, send error massage to signup page
			List<FieldError> fieldErrors = result.getFieldErrors();
			StringBuilder errorMassage = new StringBuilder();
			for (FieldError e : fieldErrors) {
				errorMassage.append(messageSource.getMessage(e, null) + "\n");
			}
			model.addAttribute("error", errorMassage.toString());
			return "patientSignup";
		}
		// validation success, insert this patient user to database
		PatientRepository pr = new PatientRepository();
		pr.addUser(patient);
		model.addAttribute("massage", "Successful create user: " + patient.getUsername());
		return "redirect:/";
	}

	@GetMapping("/doctor")
	public String getSignDoctor() {
		return "doctorSignup";
	}

	@PostMapping("/doctor")
	public String getSignDoctor(HttpServletRequest req, @Valid @ModelAttribute Doctor doctor, BindingResult result,
			@RequestParam("hno") String hno, @RequestParam("street") String street, @RequestParam("city") String city,
			Model model) {
		Address address = new Address();
		address.setHno(hno);
		address.setStreet(street);
		address.setCity(city);
		doctor.setAddress(address);
		if (result.hasErrors()) {
			// validation fail, send error massage to signup page
			List<FieldError> fieldErrors = result.getFieldErrors();
			StringBuilder errorMassage = new StringBuilder();
			for (FieldError e : fieldErrors) {
				errorMassage.append(messageSource.getMessage(e, null) + "\n");
			}
			model.addAttribute("error", errorMassage.toString());
			return "doctorSignup";
		}
		// validation success, insert this doctor user to database
		DoctorRepository dr = new DoctorRepository();
		dr.addUser(doctor);
		model.addAttribute("massage", "Successful create user: " + doctor.getUsername());
		return "redirect:/";
	}
}
