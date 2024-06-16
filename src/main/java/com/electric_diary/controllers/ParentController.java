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

import com.electric_diary.entities.ParentEntity;
import com.electric_diary.services.ParentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/parents")
public class ParentController {

	@Autowired
	protected ParentService parentService;

	@PostMapping
	public ResponseEntity<?> createParent(@Valid @RequestBody ParentEntity parentBody, BindingResult result) {
		return parentService.createParent(parentBody, result);
	}

	@GetMapping
	public ResponseEntity<?> getAllParents() {
		return parentService.getAllParents();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getParentById(@PathVariable String id) {
		return parentService.getParentById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateParent(@PathVariable String id, @RequestBody ParentEntity parentBody) {
		return parentService.updateParent(id, parentBody);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteParent(@PathVariable String id) {
		return parentService.deleteParent(id);
	}
}
