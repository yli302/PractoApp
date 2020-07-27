package com.java.dao;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.java.config.HibernateConfig;
import com.java.dto.Patient;

public class PatientRepository {
	public void addUser(Patient p) {
		Session s = HibernateConfig.sf.openSession();
		Transaction tx = s.beginTransaction();
		s.save(p);
		tx.commit();
		s.close();
	}

	public Optional<Patient> checkUserExist(String username, String password) {
		Session s = HibernateConfig.sf.openSession();
		// get id of unique username of a patient
		Query<Integer> q = s.createQuery("select id from Patient where username = :name and password = :pw",
				Integer.class);
		q.setParameter("name", username);
		q.setParameter("pw", password);
		Integer id = q.uniqueResult();
		System.out.println("************id is " + id);
		// get Patient obj of this id
		Patient p = new Patient();
		if (id != null)
			p = s.get(Patient.class, id);
		else
			p = null;
		s.close();
		System.out.println("************p is " + p);
		// return this Patient or empty.
		if (p != null) {
			return Optional.of(p);
		}
		return Optional.empty();
	}
}
