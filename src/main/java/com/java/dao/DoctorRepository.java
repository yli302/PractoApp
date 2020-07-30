package com.java.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.config.HibernateConfig;
import com.java.dto.Doctor;

@Repository
@Transactional
public class DoctorRepository {
	@Autowired
	HibernateTemplate template;

	public void addUser(Doctor p) {
		/*
		 * Session s = HibernateConfig.sf.openSession(); Transaction tx =
		 * s.beginTransaction(); s.save(p); tx.commit(); s.close();
		 */
		template.save(p);
	}

	public Doctor getUser(Integer id) {
		Doctor d = template.get(Doctor.class, id);
		return d;
	}

	public Optional<Doctor> checkUserExist(String username, String password) {
		/*
		 * Session s = HibernateConfig.sf.openSession(); // get id of unique username of
		 * a patient Query<Integer> q = s.
		 * createQuery("select id from Doctor where username = :name and password = :pw"
		 * , Integer.class); q.setParameter("name", username); q.setParameter("pw",
		 * password); Integer id = q.uniqueResult();
		 * System.out.println("************id is " + id); // get Patient obj of this id
		 * Doctor p = new Doctor(); if (id != null) p = s.get(Doctor.class, id); else p
		 * = null; s.close(); System.out.println("************p is " + p); // return
		 * this Patient or empty. if (p != null) { return Optional.of(p); } return
		 * Optional.empty();
		 */
		DetachedCriteria c = DetachedCriteria.forClass(Doctor.class);
		c.add(Restrictions.eq("username", username));
		c.add(Restrictions.eq("password", password));
		// c.setProjection(Projections.property("id"));
		List<Doctor> list = (List<Doctor>) template.findByCriteria(c);
		Doctor p = null;
		if (!list.isEmpty()) {
			p = list.get(0);
		}

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

		return (List<Doctor>) criteria.list().stream().distinct().collect(Collectors.toList());

		// this will return a list with duplicate data.
		/*
		 * DetachedCriteria c = DetachedCriteria.forClass(Doctor.class); if
		 * (!StringUtils.isEmpty(name)) { c.add(Restrictions.eq("name", name)); } if
		 * (!StringUtils.isEmpty(city)) c.add(Restrictions.eq("address.city", city)); if
		 * (!StringUtils.isEmpty(speciality)) c.add(Restrictions.in("speciality",
		 * speciality)); return (List<Doctor>) template.findByCriteria(c);
		 */
	}
}
