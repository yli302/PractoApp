package com.java.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.Patient;

@Repository
@Transactional
public class PatientRepository {
	@Autowired
	HibernateTemplate template;

	public void addUser(Patient p) {
		/*
		 * Session s = HibernateConfig.sf.openSession(); Transaction tx =
		 * s.beginTransaction(); s.save(p); tx.commit(); s.close();
		 */
		template.save(p);
	}

	public Optional<Patient> checkUserExist(String username, String password) {
		/*
		 * Session s = HibernateConfig.sf.openSession(); // get id of unique username of
		 * a patient Query<Integer> q = s.
		 * createQuery("select id from Patient where username = :name and password = :pw"
		 * , Integer.class); q.setParameter("name", username); q.setParameter("pw",
		 * password); Integer id = q.uniqueResult();
		 * System.out.println("************id is " + id); // get Patient obj of this id
		 * Patient p = new Patient(); if (id != null) p = s.get(Patient.class, id); else
		 * p = null; s.close(); System.out.println("************p is " + p); // return
		 * this Patient or empty. if (p != null) { return Optional.of(p); } return
		 * Optional.empty();
		 */
		DetachedCriteria c = DetachedCriteria.forClass(Patient.class);
		c.add(Restrictions.eq("username", username));
		c.add(Restrictions.eq("password", password));
		// c.setProjection(Projections.property("id"));
		List<Patient> list = (List<Patient>) template.findByCriteria(c);
		Patient p = null;
		if (!list.isEmpty()) {
			p = list.get(0);
		}

		if (p != null) {
			return Optional.of(p);
		}
		return Optional.empty();
	}
}
