package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.electric_diary.entities.TeacherEntity;
import com.electric_diary.services.TeacherService;

@RestController
@RequestMapping(path = "/api/v1/teachers")
public class TeacherController {

	@Autowired
	protected TeacherService teacherService;

	@PostMapping
	public TeacherEntity createTeacher(@RequestParam String firstName, @RequestParam String lastName) {
		return teacherService.createTeacher(firstName, lastName);
	}

	@GetMapping
	public Iterable<TeacherEntity> getAllTeachers() {
		return teacherService.getAllTeachers();
	}

	@GetMapping("/{id}")
	public TeacherEntity getTeacherById(@PathVariable String id) {
		return teacherService.getTeacherById(id);
	}

	@PutMapping("/{id}")
	public TeacherEntity updateTeacher(@PathVariable String id, @RequestParam String firstName, @RequestParam String lastName) {
		return teacherService.updateTeacher(id, firstName, lastName);
	}

	@DeleteMapping("/{id}")
	public TeacherEntity deleteTeacher(@PathVariable String id) {
		return teacherService.deleteTeacher(id);
	}
}
