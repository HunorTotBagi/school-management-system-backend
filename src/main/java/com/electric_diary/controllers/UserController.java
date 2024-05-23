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
	public UserEntity createUser(@RequestBody UserEntity userBody) {
		return userService.createUser(userBody);
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
	public UserEntity updateUser(@PathVariable String id, @RequestBody UserEntity userBody) {
		return userService.updateUser(id, userBody);
	}

	@DeleteMapping("/{id}")
	public UserEntity deleteUser(@PathVariable String id) {
		return userService.deleteUser(id);
	}
}
