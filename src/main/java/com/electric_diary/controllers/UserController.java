package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.electric_diary.entities.UserEntity;
import com.electric_diary.services.UserService;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

	@Autowired
	protected UserService userService;

	@PostMapping
	public UserEntity createUser(@RequestParam String username, @RequestParam String password) {
		return userService.createUser(username, password);
	}

	@GetMapping
	public Iterable<UserEntity> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public UserEntity getUserById(@PathVariable String id) {
		return userService.getUserById(id);
	}

	@PutMapping("/{id}")
	public UserEntity updateUser(@PathVariable String id, @RequestParam String username, @RequestParam String password) {
		return userService.updateUser(id, username, password);
	}

	@DeleteMapping("/{id}")
	public UserEntity deleteUser(@PathVariable String id) {
		return userService.deleteUser(id);
	}
}
