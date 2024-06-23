package com.electric_diary.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotBlank(message = "Username cannot be blank")
	@NotNull(message = "Username name must be provided.")
	@Size(min = 2, max = 30, message = "Username must be between {min} and {max} characters long.")
	private String username;

	@NotBlank(message = "Username cannot be blank")
	@NotNull(message = "Password must be provided.")
	@Size(min = 2, max = 30, message = "Password must be between {min} and {max} characters long.")
	private String password;

	public UserEntity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
