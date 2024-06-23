package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		return new ResponseEntity<UserEntity>(userService.createUser(userBody, result), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Iterable<UserEntity>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable String id) {
		return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserEntity> updateUser(@PathVariable String id, @RequestBody UserEntity userBody) {
		return new ResponseEntity<>(userService.updateUser(id, userBody), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserEntity> deleteUser(@PathVariable String id) {
		return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
	}
}
