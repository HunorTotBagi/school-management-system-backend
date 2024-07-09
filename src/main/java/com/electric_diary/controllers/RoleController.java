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

	@GetMapping("/{id}")
	public ResponseEntity<RoleEntity> getRoleById(@PathVariable String id) {
		return new ResponseEntity<>(roleService.getRoleById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<RoleEntity> updateRole(@PathVariable String id, @RequestBody RoleEntity roleBody) {
		return new ResponseEntity<>(roleService.updateRole(id, roleBody), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RoleEntity> deleteRole(@PathVariable String id) {
		return new ResponseEntity<>(roleService.deleteRole(id), HttpStatus.OK);
	}
}
