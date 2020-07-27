package com.java.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.java.config.HibernateConfig;
import com.java.dto.Appointment;

public class AppointmentRepository {
	public void makeNewAppointment(Appointment a) {
		Session s = HibernateConfig.sf.openSession();
		Transaction tx = s.beginTransaction();
		s.save(a);
		tx.commit();
		s.close();
	}

	public void rateAppointment(Appointment a) {
		Session s = HibernateConfig.sf.openSession();
		Transaction tx = s.beginTransaction();
		s.update(a);
		tx.commit();
		s.close();
	}

	public List<Appointment> getAllAppointments(int patientId) {
		Session s = HibernateConfig.sf.openSession();
		Criteria criteria = s.createCriteria(Appointment.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		return criteria.list();
	}
}
