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

import com.electric_diary.entities.ClassEntity;
import com.electric_diary.services.ClassService;

@RestController
@RequestMapping(path = "/api/v1/classes")
public class ClassController {

	@Autowired
	protected ClassService classService;

	@PostMapping
	public ResponseEntity<ClassEntity> createClass(@RequestBody ClassEntity classBody, BindingResult result) {
		return classService.createClass(classBody, result);
	}

	@GetMapping
	public ResponseEntity<Iterable<ClassEntity>> getAllClasses() {
		return classService.getAllClasses();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClassEntity> getClassById(@PathVariable String id) {
		return classService.getClassById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClassEntity> updateClass(@PathVariable String id, @RequestBody ClassEntity classBody) {
		return classService.updateClass(id, classBody);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ClassEntity> deleteClass(@PathVariable String id) {
		return classService.deleteClass(id);
	}

}
