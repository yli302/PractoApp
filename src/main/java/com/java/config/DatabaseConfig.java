package com.java.config;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.Oracle12cDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.java.dto.Appointment;
import com.java.dto.Doctor;
import com.java.dto.Patient;

@Configuration
// proxyTargetClass:  Repository interface exist, then set to false.
@EnableTransactionManagement(proxyTargetClass = true)
public class DatabaseConfig {
	// HibernateTemplate is a class of spring to manage hibernate
	@Bean
	public HibernateTemplate template() throws IOException {
		HibernateTemplate template = new HibernateTemplate();
//		template.setCheckWriteOperations(false);
		template.setSessionFactory(sessionFactory());
		return template;
	}

	@Bean
	public SessionFactory sessionFactory() throws IOException {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setAnnotatedClasses(Doctor.class, Patient.class, Appointment.class);
		factoryBean.setDataSource(datasource());
		Properties p = new Properties();
		p.setProperty(Environment.SHOW_SQL, "true");
		p.setProperty(Environment.HBM2DDL_AUTO, "update");
		p.setProperty(Environment.DIALECT, Oracle12cDialect.class.getName());
		factoryBean.setHibernateProperties(p);
		factoryBean.afterPropertiesSet(); // create ur session factory obj
		return factoryBean.getObject();
	}

	@Bean
	public HibernateTransactionManager transactionManager() throws IOException {
		HibernateTransactionManager manager = new HibernateTransactionManager();
		// manager.setDataSource(datasource());
		manager.setSessionFactory(sessionFactory());
		return manager;
	}

	@Bean
	public DriverManagerDataSource datasource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		ds.setUsername("system");
		ds.setPassword("123456");
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		return ds;
	}
}
