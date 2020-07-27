package com.java.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.java.dto.Appointment;
import com.java.dto.Doctor;
import com.java.dto.Patient;

//Guys for this doctor app, plz implement the  insert doctor and 
//search doctor operation (by name, by location, by speciality , and by combination ) 
//using spring mvc plus form validations. As a homework.. 

public class HibernateConfig {
	private static Configuration cfg;
	public static SessionFactory sf;
	static {
		cfg = new Configuration().addPackage("com.java.dto");
		Properties p = new Properties();
		p.setProperty(Environment.SHOW_SQL, "true");
		p.setProperty(Environment.HBM2DDL_AUTO, "validate");
		p.setProperty(Environment.URL, "jdbc:oracle:thin:@localhost:1521:orcl");
		p.setProperty(Environment.USER, "system");
		p.setProperty(Environment.PASS, "123456");
		p.setProperty(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
		p.setProperty(Environment.DIALECT, "org.hibernate.dialect.Oracle12cDialect");
		cfg.addAnnotatedClass(Doctor.class);
		cfg.addAnnotatedClass(Patient.class);
		cfg.addAnnotatedClass(Appointment.class);
//		cfg.configure().addProperties(p);
		StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder().applySettings(p);
		sf = cfg.buildSessionFactory(sb.build());
	}
}