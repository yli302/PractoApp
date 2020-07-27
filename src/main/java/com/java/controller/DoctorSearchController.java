package com.java.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dao.AppointmentRepository;
import com.java.dao.DoctorRepository;
import com.java.dto.Appointment;
import com.java.dto.Doctor;
import com.java.dto.DoctorSearchCriteria;
import com.java.dto.Patient;

@Controller
@RequestMapping("/search")
public class DoctorSearchController {
	@GetMapping
	public String getDoctorSearchPage() {
		return "doctorSearch";
	}

	@PostMapping
	public String searchDoctor(@ModelAttribute DoctorSearchCriteria searchCriteria, Model model) {
		DoctorRepository dr = new DoctorRepository();
		List<Doctor> list = dr.getAllDoctors(searchCriteria.getName(), searchCriteria.getCity(),
				searchCriteria.getSpeciality());
		model.addAttribute("list", list);
		return "searchResult";
	}

	@GetMapping("appointment/{id}")
	public String makeAppointment(@PathVariable("id") int doctorId, Model model) {
		model.addAttribute("doctorId", doctorId);
		return "newAppointment";
	}

	@PostMapping("appointment")
	public String makeAppointment(@ModelAttribute Appointment appointment, HttpSession session, Model model) {
		AppointmentRepository ar = new AppointmentRepository();
		Patient p = (Patient) session.getAttribute("patient");
		appointment.setPatientId(p.getId());
		System.out.println(appointment);
		ar.makeNewAppointment(appointment);
		return "redirect:/";
	}

	@GetMapping("myAppointment")
	public String myAppointment(HttpSession session, Model model) {
		AppointmentRepository ar = new AppointmentRepository();
		Patient p = (Patient) session.getAttribute("patient");
		List<Appointment> appointments = ar.getAllAppointments(p.getId());
		model.addAttribute("list", appointments);
		return "myAppointment";
	}

	@PostMapping("rate")
	public String rateAppointment(@ModelAttribute Appointment appointment) {
		AppointmentRepository ar = new AppointmentRepository();
		ar.rateAppointment(appointment);
		return "redirect:/";
	}
}
