package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		return new ResponseEntity<>(studentService.createStudent(studentBody, result), HttpStatus.OK);
	}

	@GetMapping("/student")
	@JsonView(Views.Student.class)
	public ResponseEntity<Iterable<StudentEntity>> getAllStudentsForStudents() {
		return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
	}

	@GetMapping("/parent")
	@JsonView(Views.Parent.class)
	public ResponseEntity<Iterable<StudentEntity>> getAllStudentsForParent() {
		return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentEntity> getStudentById(@PathVariable String id) {
		return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentEntity> updateStudent(@PathVariable String id, @RequestBody StudentEntity studentBody) {
		return new ResponseEntity<>(studentService.updateStudent(id, studentBody), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<StudentEntity> deleteStudent(@PathVariable String id) {
		return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.OK);
	}
}
