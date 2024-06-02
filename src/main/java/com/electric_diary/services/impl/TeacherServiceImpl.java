package com.electric_diary.services.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.electric_diary.entities.TeacherEntity;
import com.electric_diary.repositories.TeacherRepository;
import com.electric_diary.services.TeacherService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TeacherServiceImpl implements TeacherService {

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected TeacherRepository teacherRepository;

	@Override
	public ResponseEntity<?> createTeacher(TeacherEntity teacherBody, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		TeacherEntity teacher = new TeacherEntity();
		teacher.setFirstName(teacherBody.getFirstName());
		teacher.setLastName(teacherBody.getLastName());
		teacherRepository.save(teacher);
		return new ResponseEntity<>(teacher, HttpStatus.OK);
	}

	@Override
	public Iterable<TeacherEntity> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public TeacherEntity getTeacherById(String id) {
		return teacherRepository.findById(Integer.parseInt(id)).get();
	}

	@Override
	public TeacherEntity updateTeacher(String id, TeacherEntity teacherBody) {
		TeacherEntity teacher = teacherRepository.findById(Integer.parseInt(id)).get();
		if (teacher != null) {
			teacher.setFirstName(teacherBody.getFirstName());
			teacher.setLastName(teacherBody.getLastName());
			teacherRepository.save(teacher);
			return teacher;
		}
		return new TeacherEntity();
	}

	@Override
	public TeacherEntity deleteTeacher(String id) {
		TeacherEntity teacher = teacherRepository.findById(Integer.parseInt(id)).get();
		if (teacher != null) {
			teacherRepository.delete(teacher);
			return teacher;
		}
		return new TeacherEntity();
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}
}
