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
	public TeacherEntity createTeacher(@RequestBody TeacherEntity teacherBody) {
		return teacherService.createTeacher(teacherBody);
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
	public TeacherEntity updateTeacher(@PathVariable String id, @RequestBody TeacherEntity teacherBody) {
		return teacherService.updateTeacher(id, teacherBody);
	}

	@DeleteMapping("/{id}")
	public TeacherEntity deleteTeacher(@PathVariable String id) {
		return teacherService.deleteTeacher(id);
	}
}
