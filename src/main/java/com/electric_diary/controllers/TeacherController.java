package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.electric_diary.entities.TeacherEntity;
import com.electric_diary.handlers.TeacherHandler;

@RestController
@RequestMapping(path = "/api/v1/teachers")
public class TeacherController {

	@Autowired
	protected TeacherHandler teacherHandler;

	@PostMapping
	public TeacherEntity createTeacher(@RequestParam String firstName, @RequestParam String lastName) {
		return teacherHandler.createTeacher(firstName, lastName);
	}

	@GetMapping
	public Iterable<TeacherEntity> getAllTeachers() {
		return teacherHandler.getAllTeachers();
	}

	@GetMapping("/{id}")
	public TeacherEntity getTeacherById(@PathVariable String id) {
		return teacherHandler.getTeacherById(id);
	}

	@PutMapping("/{id}")
	public TeacherEntity updateTeacher(@PathVariable String id, @RequestParam String firstName, @RequestParam String lastName) {
		return teacherHandler.updateTeacher(id, firstName, lastName);
	}

	@DeleteMapping("/{id}")
	public TeacherEntity deleteTeacher(@PathVariable String id) {
		return teacherHandler.deleteTeacher(id);
	}
}
