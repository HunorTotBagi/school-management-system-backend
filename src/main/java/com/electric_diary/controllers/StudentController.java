package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	public StudentEntity createStudent(@RequestBody StudentEntity studentBody) {
		return studentService.createStudent(studentBody);
	}

	@GetMapping("/student")
	@JsonView(Views.Student.class)
	public Iterable<StudentEntity> getAllStudentsForStudents() {
		return studentService.getAllStudents();
	}
	
	@GetMapping("/parent")
	@JsonView(Views.Parent.class)
	public Iterable<StudentEntity> getAllStudentsForParent() {
		return studentService.getAllStudents();
	}

	@GetMapping("/{id}")
	public StudentEntity getStudentById(@PathVariable String id) {
		return studentService.getStudentById(id);
	}

	@PutMapping("/{id}")
	public StudentEntity updateStudent(@PathVariable String id, @RequestBody StudentEntity studentBody) {
		return studentService.updateStudent(id, studentBody);
	}

	@DeleteMapping("/{id}")
	public StudentEntity deleteStudent(@PathVariable String id) {
		return studentService.deleteStudent(id);
	}
}
