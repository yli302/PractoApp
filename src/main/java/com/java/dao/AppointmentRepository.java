package com.java.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.config.HibernateConfig;
import com.java.dto.Appointment;
import com.java.dto.Patient;

@Repository
@Transactional
public class AppointmentRepository {
	@Autowired
	HibernateTemplate template;

	public void makeNewAppointment(Appointment a) {
		/*
		 * Session s = HibernateConfig.sf.openSession(); Transaction tx =
		 * s.beginTransaction(); s.save(a); tx.commit(); s.close();
		 */
		template.save(a);
	}

	public Appointment getAppointment(int id) {
		return template.get(Appointment.class, id);
	}

	public void rateAppointment(Appointment a) {
		/*
		 * Session s = HibernateConfig.sf.openSession(); Transaction tx =
		 * s.beginTransaction(); s.update(a); tx.commit(); s.close();
		 */

//		Session session = template.getSessionFactory().openSession();
//		session.saveOrUpdate(Appointment.class, a);
//		session.flush();
//		session.close();
		template.update(a);
	}

	@SuppressWarnings("unchecked")
	public List<Appointment> getAllAppointments(Patient p) {
		Session s = HibernateConfig.sf.openSession();
		Criteria criteria = s.createCriteria(Appointment.class);
		criteria.add(Restrictions.eq("patient", p));
		return (List<Appointment>) criteria.list().stream().distinct().collect(Collectors.toList());

		/*
		 * DetachedCriteria dc = DetachedCriteria.forClass(Appointment.class);
		 * dc.add(Restrictions.eq("patientId", patientId)); List<Appointment> list =
		 * (List<Appointment>) template.findByCriteria(dc);
		 */
//		return list;
	}
}
