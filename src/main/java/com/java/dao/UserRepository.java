package com.java.dao;

import org.springframework.data.repository.CrudRepository;

import com.java.dto.User;

public interface UserRepository extends CrudRepository<User, String>{

}
