package com.java.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_info")
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	String username;
	String password;
	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "authority", joinColumns = @JoinColumn(referencedColumnName = "username", name = "username"))
	List<String> authorities = new ArrayList<String>();// user_authorities

}
