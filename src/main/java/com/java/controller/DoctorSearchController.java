package com.java.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dao.AppointmentRepository;
import com.java.dao.DoctorRepository;
import com.java.dto.Appointment;
import com.java.dto.Doctor;
import com.java.dto.DoctorSearchCriteria;
import com.java.dto.Patient;

@Controller
@RequestMapping("/search")
public class DoctorSearchController {
	@Autowired
	DoctorRepository dr;
	@Autowired
	AppointmentRepository ar;

	@GetMapping
	public String getDoctorSearchPage() {
		return "doctorSearch";
	}

	@PostMapping
	public String searchDoctor(@ModelAttribute DoctorSearchCriteria searchCriteria, Model model) {
		List<Doctor> list = dr.getAllDoctors(searchCriteria.getName(), searchCriteria.getCity(),
				searchCriteria.getSpeciality());
		model.addAttribute("list", list);
		System.out.println("****************" + list);
		return "searchResult";
	}

	@GetMapping("appointment/{id}")
	public String makeAppointment(@PathVariable("id") int doctorId, Model model) {
		Doctor d = dr.getUser(doctorId);
		model.addAttribute("doctor", d);
		return "newAppointment";
	}

	@PostMapping("appointment")
	public String makeAppointment(@RequestParam("time") String time, @RequestParam("doctorId") int doctorId,
			HttpSession session, Model model) {
		Patient p = (Patient) session.getAttribute("patient");
		Appointment appointment = new Appointment();
		appointment.setPatient(p);

		Doctor doctor = dr.getUser(doctorId);
		Map<String, Boolean> schedule = doctor.getSchedule();
		schedule.put(time, true);
		doctor.setSchedule(schedule);

		System.out.println("doct: " + doctor);
		appointment.setDoctor(doctor);
		appointment.setTime(time);

		ar.makeNewAppointment(appointment);
		return "redirect:/";
	}

	@GetMapping("myAppointment")
	public String myAppointment(HttpSession session, Model model) {
		Patient p = (Patient) session.getAttribute("patient");
		List<Appointment> appointments = ar.getAllAppointments(p);
		model.addAttribute("list", appointments);
		return "myAppointment";
	}

	@PostMapping("rate")
	public String rateAppointment(@RequestParam int id, @RequestParam int rate) {
		Appointment ap = ar.getAppointment(id);
		ap.setRate(rate);
		ar.rateAppointment(ap);
		return "redirect:/";
	}
}
