package com.java.config;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.java.dto.Appointment;
import com.java.dto.Doctor;
import com.java.dto.Patient;
import com.java.dto.User;

@Configuration
// proxyTargetClass:  Repository interface exist, then set to false.
@EnableTransactionManagement(proxyTargetClass = false)
public class DatabaseConfig {
	// HibernateTemplate is a class of spring to manage hibernate
	
	@Autowired DatabaseUtil util;
	/*
	 * @Bean public HibernateTemplate template() throws IOException {
	 * HibernateTemplate template = new HibernateTemplate(); //
	 * template.setCheckWriteOperations(false);
	 * template.setSessionFactory(sessionFactory()); return template; }
	 */

	private SessionFactory sessionFactory() throws IOException {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setAnnotatedClasses(Doctor.class, Patient.class, Appointment.class, User.class);
		factoryBean.setDataSource(datasource());
		Properties p = new Properties();
		p.setProperty(Environment.SHOW_SQL, "true");
		p.setProperty(Environment.HBM2DDL_AUTO, util.getHbm2ddlauto());
		p.setProperty(Environment.DIALECT, util.getDialect());
		factoryBean.setHibernateProperties(p);
		factoryBean.afterPropertiesSet(); // create ur session factory obj
		return factoryBean.getObject();
	}
	
		@Bean
			public EntityManagerFactory entityManagerFactory() throws IOException {
			return sessionFactory().unwrap(EntityManagerFactory.class);
		}
	@Bean
	public JpaTransactionManager transactionManager() throws IOException {
		JpaTransactionManager manager = new JpaTransactionManager();
		// manager.setDataSource(datasource());
		manager.setEntityManagerFactory(entityManagerFactory());
		return manager;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySource() {
		PropertySourcesPlaceholderConfigurer c= new PropertySourcesPlaceholderConfigurer();
		return c;
	}
	@Bean
	public DriverManagerDataSource datasource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(util.getUrl());
		ds.setUsername(util.getUsername());
		ds.setPassword(util.getPassword());
		ds.setDriverClassName(util.getDriverClassName());
		return ds;
	}
}
