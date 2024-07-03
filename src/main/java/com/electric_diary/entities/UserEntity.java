package com.electric_diary.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	protected Integer id;
	
    @Column(unique = true, nullable = false, name = "name")
	@NotBlank(message = "Name cannot be blank")
	@NotNull(message = "Name name must be provided.")
	@Size(min = 2, max = 30, message = "Name must be between {min} and {max} characters long.")
	protected String name;
	
	@NotBlank(message = "Last name cannot be blank")
	@NotNull(message = "Last name must be provided.")
	@Size(min = 2, max = 30, message = "Last name must be between {min} and {max} characters long.")
	@Column(name = "last_name")
	protected String lastName;

	@JsonIgnore
	@NotBlank(message = "Password cannot be blank")
	@NotNull(message = "Password must be provided.")
	@Size(min = 2, max = 30, message = "Password must be between {min} and {max} characters long.")
	@Column(name = "password")
	protected String password;
	
	@Column(name = "email")
	protected String email;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	protected RoleEntity role;

	public UserEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}
}