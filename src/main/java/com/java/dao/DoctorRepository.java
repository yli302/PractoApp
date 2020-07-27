package com.java.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.java.config.HibernateConfig;
import com.java.dto.Doctor;

public class DoctorRepository {
	public void addUser(Doctor p) {
		Session s = HibernateConfig.sf.openSession();
		Transaction tx = s.beginTransaction();
		s.save(p);
		tx.commit();
		s.close();
	}

	public Optional<Doctor> checkUserExist(String username, String password) {
		Session s = HibernateConfig.sf.openSession();
		// get id of unique username of a patient
		Query<Integer> q = s.createQuery("select id from Doctor where username = :name and password = :pw",
				Integer.class);
		q.setParameter("name", username);
		q.setParameter("pw", password);
		Integer id = q.uniqueResult();
		System.out.println("************id is " + id);
		// get Patient obj of this id
		Doctor p = new Doctor();
		if (id != null)
			p = s.get(Doctor.class, id);
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

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Doctor> getAllDoctors(String name, String city, String speciality) {
		Session s = HibernateConfig.sf.openSession();
		Criteria criteria = s.createCriteria(Doctor.class);
		if (!"".equals(name))
			criteria.add(Restrictions.eq("name", name));
		if (!"".equals(city))
			criteria.add(Restrictions.eq("address.city", city));
		if (!"".equals(speciality))
			criteria.add(Restrictions.in("speciality", speciality));
		return criteria.list();
	}
}
