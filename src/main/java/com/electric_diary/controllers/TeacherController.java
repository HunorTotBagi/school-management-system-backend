package com.electric_diary.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.electric_diary.entities.TeacherEntity;
import com.electric_diary.services.TeacherService;

@RestController
@RequestMapping(path = "/api/v1/teachers")
public class TeacherController {

	@Autowired
	protected TeacherService teacherService;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@PostMapping
	public TeacherEntity createTeacher(@RequestBody TeacherEntity teacherBody) {
		logger.debug("This is a debug message");
		return teacherService.createTeacher(teacherBody);
	}

	@GetMapping
	public Iterable<TeacherEntity> getAllTeachers() {
		logger.info("This is an info message");
		return teacherService.getAllTeachers();
	}

	@GetMapping("/{id}")
	public TeacherEntity getTeacherById(@PathVariable String id) {
		logger.warn("This is a warn message");
		return teacherService.getTeacherById(id);
	}

	@PutMapping("/{id}")
	public TeacherEntity updateTeacher(@PathVariable String id, @RequestBody TeacherEntity teacherBody) {
		logger.error("This is an error message");
		return teacherService.updateTeacher(id, teacherBody);
	}

	@DeleteMapping("/{id}")
	public TeacherEntity deleteTeacher(@PathVariable String id) {
		return teacherService.deleteTeacher(id);
	}
}
