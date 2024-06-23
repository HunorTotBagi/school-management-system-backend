package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electric_diary.entities.UserEntity;
import com.electric_diary.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

	@Autowired
	protected UserService userService;

	@PostMapping
	public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity userBody, BindingResult result) {
		return userService.createUser(userBody, result);
	}

	@GetMapping
	public ResponseEntity<Iterable<UserEntity>> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable String id) {
		return userService.getUserById(id);
	}

	@PutMapping("/{id}")
	public UserEntity updateUser(@PathVariable String id, @RequestBody UserEntity userBody) {
		return userService.updateUser(id, userBody);
	}

	@DeleteMapping("/{id}")
	public UserEntity deleteUser(@PathVariable String id) {
		return userService.deleteUser(id);
	}
}
