package com.java.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.java.dto.Address;
import com.java.dto.Doctor;
import com.java.dto.Patient;

@Controller
@RequestMapping("/signup")
public class SignupController {
	@Autowired
	PatientRepository pr;
	@Autowired
	DoctorRepository dr;

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/patient")
	public String getSignPatient(@RequestParam String username) {
		System.out.println(username);
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
		pr.save(patient);
		model.addAttribute("massage", "Successful create user: " + patient.getUsername());
		return "redirect:/";
	}

	@GetMapping("/doctor")
	public String getSignDoctor() {
		return "doctorSignup";
	}

	@PostMapping("/doctor")
	@PreAuthorize("hasRole('DOCTOR')")
	public String getSignDoctor(HttpServletRequest req, @Valid @ModelAttribute Doctor doctor, BindingResult result,
			@RequestParam("hno") String hno, @RequestParam("street") String street, @RequestParam("city") String city,
			@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
			@RequestParam("timePeriod") int timePeriod, Model model) {
		Address address = new Address();
		address.setHno(hno);
		address.setStreet(street);
		address.setCity(city);
		doctor.setAddress(address);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		LocalDateTime start = LocalDateTime.now();
		LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);
		start = start.withHour(startDateTime.getHour());
		start = start.withMinute(startDateTime.getMinute());
		LocalDateTime temp = start;

		LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);
		LocalDateTime end = LocalDateTime.now();
		end = end.withHour(endDateTime.getHour());
		end = end.withMinute(endDateTime.getMinute());

		Map<String, Boolean> schedule = new LinkedHashMap<String, Boolean>();
		for (int i = 0; i < 3; ++i) {
			start = temp.plusDays(i);
			System.out.println("in for");
			end.plusDays(i);
			System.out.println(start);
			System.out.println(end);
			while (start.isBefore(end)) {
				System.out.println("in while: " + start);
				schedule.put(getStringDateTime(start), false);
				start = start.plusMinutes(timePeriod);
			}
		}
		System.out.println(schedule);
		doctor.setSchedule(schedule);

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
		dr.save(doctor);
		model.addAttribute("massage", "Successful create user: " + doctor.getUsername());
		return "redirect:/";
	}

	private String getStringDateTime(LocalDateTime time) {
		return time.getMonth() + "-" + time.getDayOfMonth() + " " + time.getHour() + ":" + time.getMinute();
	}
}
