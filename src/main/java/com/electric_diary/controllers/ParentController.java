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

import com.electric_diary.entities.ParentEntity;
import com.electric_diary.services.ParentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/parents")
public class ParentController {

	@Autowired
	protected ParentService parentService;

	@PostMapping
	public ResponseEntity<ParentEntity> createParent(@Valid @RequestBody ParentEntity parentBody,
			BindingResult result) {
		return new ResponseEntity<ParentEntity>(parentService.createParent(parentBody, result), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Iterable<ParentEntity>> getAllParents() {
		return new ResponseEntity<>(parentService.getAllParents(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ParentEntity> getParentById(@PathVariable String id) {
		return new ResponseEntity<>(parentService.getParentById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ParentEntity> updateParent(@PathVariable String id, @RequestBody ParentEntity parentBody) {
		return new ResponseEntity<>(parentService.updateParent(id, parentBody), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ParentEntity> deleteParent(@PathVariable String id) {
		return new ResponseEntity<>(parentService.deleteParent(id), HttpStatus.OK);
	}
}
