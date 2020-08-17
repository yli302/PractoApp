package com.java.config;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.java.dao.UserRepository;
import com.java.dto.User;

@Component
@DependsOn("dp")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired UserRepository rep;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> o=rep.findById(username);
		if(!o.isPresent()) {
			throw new UsernameNotFoundException("Username does not exist");
		}else {
			User u1= o.get();
			return new org.springframework.security.core.userdetails.User(u1.getUsername(),
			u1.getPassword(), u1.getAuthorities().stream().map(x-> new SimpleGrantedAuthority(x)).collect(Collectors.toList()) );
		}
	}

}
