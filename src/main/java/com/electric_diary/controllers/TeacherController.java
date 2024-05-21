package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.electric_diary.entities.TeacherEntity;
import com.electric_diary.handlers.TeacherHandler;

@RestController
@RequestMapping(path = "/api/v1/teachers")
public class TeacherController {

	@Autowired
	protected TeacherHandler teacherHandler;

	@RequestMapping(method = RequestMethod.POST)
	public TeacherEntity createTeacher(@RequestParam String firstName, @RequestParam String lastName) {
		return teacherHandler.createTeacher(firstName, lastName);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<TeacherEntity> getAllTeachers() {
		return teacherHandler.getAllTeachers();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public TeacherEntity getTeacherById(@PathVariable String id) {
		return teacherHandler.getTeacherById(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public TeacherEntity updateTeacher(@PathVariable String id, @RequestParam String firstName, @RequestParam String lastName) {
		return teacherHandler.updateTeacher(id, firstName, lastName);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public TeacherEntity deleteTeacher(@PathVariable String id) {
		return teacherHandler.deleteTeacher(id);
	}
}
