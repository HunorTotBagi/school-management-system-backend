package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electric_diary.entities.RoleEntity;
import com.electric_diary.services.RoleService;

@RestController
@RequestMapping(path = "/api/v1/roles")
public class RoleController {

	@Autowired
	protected RoleService roleService;

	@PostMapping
	public ResponseEntity<RoleEntity> createParent(@RequestBody RoleEntity roleBody, BindingResult result) {
		return new ResponseEntity<RoleEntity>(roleService.createRole(roleBody, result), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Iterable<RoleEntity>> getAllRoles() {
		return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
	}
}
