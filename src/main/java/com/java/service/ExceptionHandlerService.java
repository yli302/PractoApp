package com.java.service;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.java.controller")
public class ExceptionHandlerService {
//	Logger logger = Logger.getLogger(ExceptionHandlerService.class);

	@ExceptionHandler(HibernateException.class)
	public void handleException(HibernateException e, HttpServletRequest req) {
//		logger.error("Exception occured for request: " + req.getRequestURL());
	}
}
