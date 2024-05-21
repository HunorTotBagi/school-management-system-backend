package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.electric_diary.entities.UserEntity;
import com.electric_diary.handlers.UserHandler;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

	@Autowired
	protected UserHandler userHandler;

	@PostMapping
	public UserEntity createUser(@RequestParam String username, @RequestParam String password) {
		return userHandler.createUser(username, password);
	}

	@GetMapping
	public Iterable<UserEntity> getAllUsers() {
		return userHandler.getAllUsers();
	}

	@GetMapping("/{id}")
	public UserEntity getUserById(@PathVariable String id) {
		return userHandler.getUserById(id);
	}

	@PutMapping("/{id}")
	public UserEntity updateUser(@PathVariable String id, @RequestParam String username, @RequestParam String password) {
		return userHandler.updateUser(id, username, password);
	}

	@DeleteMapping("/{id}")
	public UserEntity deleteUser(@PathVariable String id) {
		return userHandler.deleteUser(id);
	}
}
