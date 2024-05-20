package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.electric_diary.entities.TeacherEntity;
import com.electric_diary.repositories.TeacherRepository;

@RestController
@RequestMapping(path = "/api/v1/teachers")
public class TeacherController {

	@Autowired
	private TeacherRepository teacherRepository;

	@RequestMapping(method = RequestMethod.POST)
	public TeacherEntity addNewTeacher(@RequestParam String firstName, @RequestParam String lastName) {
		TeacherEntity teacher = new TeacherEntity();
		teacher.setFirstName(firstName);
		teacher.setLastName(lastName);
		teacherRepository.save(teacher);
		return teacher;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<TeacherEntity> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public TeacherEntity getTeacherById(@PathVariable String id) {
		return teacherRepository.findById(Integer.parseInt(id)).get();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public TeacherEntity modifyTeacher(@PathVariable String id, @RequestParam String firstName,
			@RequestParam String lastName) {
		TeacherEntity teacher = teacherRepository.findById(Integer.parseInt(id)).get();
		if (teacher != null) {
			teacher.setFirstName(firstName);
			teacher.setLastName(lastName);
			teacherRepository.save(teacher);
			return teacher;
		}
		return new TeacherEntity();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public TeacherEntity deleteTeacher(@PathVariable String id) {
		TeacherEntity teacher = teacherRepository.findById(Integer.parseInt(id)).get();
		if (teacher != null) {
			teacherRepository.delete(teacher);
			return teacher;
		}
		return new TeacherEntity();
	}
}
