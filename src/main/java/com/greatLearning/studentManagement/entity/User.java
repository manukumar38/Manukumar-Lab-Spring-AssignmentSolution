package com.greatLearning.studentManagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
//User class is an entity and is mapped to a database table users
@Entity
@Table(name = "users")
@Data
public class User {
	//Field id is mapped as primary key user_id in users table
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;

	//Each user can have many roles and every role can be mapped to many users
	//so mapped with manytomany
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	
	//Join two columns user_id from users and role_id from roles 
	//and create new table users_roles
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();

	public List<Role> getRoles() {
		return this.roles;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
