package com.java.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@Data
@PropertySource( value = "classpath:database.properties")
public class DatabaseUtil {

	@Value("${url}")
	String url;
	@Value("${ausername}")
	String username;
	@Value("${apassword}")
	String password;
	@Value("${driverClassName}")
	String driverClassName;
	@Value("${dialect}")
	String dialect;
	@Value("${hbm2ddl.auto}")
	String hbm2ddlauto;
}
