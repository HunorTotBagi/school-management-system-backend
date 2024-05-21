package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.electric_diary.entities.StudentEntity;
import com.electric_diary.handlers.StudentHandler;

@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {

	@Autowired
	protected StudentHandler studentHandler;

	@PostMapping
	public StudentEntity createStudent(@RequestParam String firstName, @RequestParam String lastName) {
		return studentHandler.createStudent(firstName, lastName);
	}

	@GetMapping
	public Iterable<StudentEntity> getAllStudents() {
		return studentHandler.getAllStudents();
	}

	@GetMapping("/{id}")
	public StudentEntity getStudentById(@PathVariable String id) {
		return studentHandler.getStudentById(id);
	}

	@PutMapping("/{id}")
	public StudentEntity updateStudent(@PathVariable String id, @RequestParam String firstName,
			@RequestParam String lastName) {
		return studentHandler.updateStudent(id, firstName, lastName);
	}

	@DeleteMapping("/{id}")
	public StudentEntity deleteStudent(@PathVariable String id) {
		return studentHandler.deleteStudent(id);
	}
}
