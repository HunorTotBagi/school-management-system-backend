package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.electric_diary.entities.ParentEntity;
import com.electric_diary.handlers.ParentService;

@RestController
@RequestMapping(path = "/api/v1/parents")
public class ParentController {

	@Autowired
	protected ParentService parentService;

	@PostMapping
	public ParentEntity createParent(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
		return parentService.createParent(firstName, lastName, email);
	}

	@GetMapping
	public Iterable<ParentEntity> getAllParents() {
		return parentService.getAllParents();
	}

	@GetMapping("/{id}")
	public ParentEntity getParentById(@PathVariable String id) {
		return parentService.getParentById(id);
	}

	@PutMapping("/{id}")
	public ParentEntity updateParent(@PathVariable String id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
		return parentService.updateParent(id, firstName, lastName, email);
	}

	@DeleteMapping("/{id}")
	public ParentEntity deleteParent(@PathVariable String id) {
		return parentService.deleteParent(id);
	}
}
