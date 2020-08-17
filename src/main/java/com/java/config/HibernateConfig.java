/*
 * package com.java.config;
 * 
 * import java.util.Properties;
 * 
 * import org.hibernate.SessionFactory; import
 * org.hibernate.boot.registry.StandardServiceRegistryBuilder; import
 * org.hibernate.cfg.Configuration; import org.hibernate.cfg.Environment;
 * 
 * import com.java.dto.Appointment; import com.java.dto.Doctor; import
 * com.java.dto.Patient;
 * 
 * //Guys for this doctor app, plz implement the insert doctor and //search
 * doctor operation (by name, by location, by speciality , and by combination )
 * //using spring mvc plus form validations. As a homework..
 * 
 * public class HibernateConfig { private static Configuration cfg; public
 * static SessionFactory sf; static { cfg = new
 * Configuration().addPackage("com.java.dto"); Properties p = new Properties();
 * p.setProperty(Environment.SHOW_SQL, "true");
 * p.setProperty(Environment.HBM2DDL_AUTO,); p.setProperty(Environment.URL, );
 * p.setProperty(Environment.USER, ); p.setProperty(Environment.PASS, );
 * p.setProperty(Environment.DRIVER, ); p.setProperty(Environment.DIALECT, );
 * cfg.addAnnotatedClass(Doctor.class); cfg.addAnnotatedClass(Patient.class);
 * cfg.addAnnotatedClass(Appointment.class); //
 * cfg.configure().addProperties(p); StandardServiceRegistryBuilder sb = new
 * StandardServiceRegistryBuilder().applySettings(p); sf =
 * cfg.buildSessionFactory(sb.build()); } }
 */