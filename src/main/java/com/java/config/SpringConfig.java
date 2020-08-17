package com.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.java.dao.UserRepository;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.java")
//@Import(SecurityConfig.class) already been added to your root context when u added the initilalizer
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SpringConfig {
	@Bean
	public InternalResourceViewResolver configureViewResolver() {
		InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
		viewResolve.setPrefix("/WEB-INF/views/");
		viewResolve.setSuffix(".jsp");

		return viewResolve;
	}
}
