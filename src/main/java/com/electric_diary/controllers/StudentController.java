package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.electric_diary.entities.StudentEntity;
import com.electric_diary.security.Views;
import com.electric_diary.services.StudentService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {

	@Autowired
	protected StudentService studentService;

	@PostMapping
	public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity studentBody, BindingResult result) {
		return studentService.createStudent(studentBody, result);
	}

	@GetMapping("/student")
	@JsonView(Views.Student.class)
	public ResponseEntity<Iterable<StudentEntity>> getAllStudentsForStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/parent")
	@JsonView(Views.Parent.class)
	public ResponseEntity<Iterable<StudentEntity>> getAllStudentsForParent() {
		return studentService.getAllStudents();
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentEntity> getStudentById(@PathVariable String id) {
		return studentService.getStudentById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentEntity> updateStudent(@PathVariable String id, @RequestBody StudentEntity studentBody) {
		return studentService.updateStudent(id, studentBody);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<StudentEntity> deleteStudent(@PathVariable String id) {
		return studentService.deleteStudent(id);
	}
}
