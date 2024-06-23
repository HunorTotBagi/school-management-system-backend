package com.electric_diary.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.TeacherEntity;
import com.electric_diary.exception.CustomBadRequestException;
import com.electric_diary.exception.NotFoundException;
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
	public TeacherEntity createTeacher(TeacherEntity teacherBody, BindingResult result) {
		if (result.hasErrors())
			throw new CustomBadRequestException(result);

		TeacherEntity teacher = new TeacherEntity();
		teacher.setFirstName(teacherBody.getFirstName());
		teacher.setLastName(teacherBody.getLastName());
		teacherRepository.save(teacher);

		return teacher;
	}

	@Override
	public Iterable<TeacherEntity> getAllTeachers() {
		Iterable<TeacherEntity> teachers = teacherRepository.findAll();
		return teachers;
	}

	@Override
	public TeacherEntity getTeacherById(String id) {
		try {
			int teacherId = Integer.parseInt(id);
			TeacherEntity teacher = teacherRepository.findById(teacherId)
					.orElseThrow(() -> new NotFoundException("Teacher", id));
			return teacher;
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
	}

	@Override
	public TeacherEntity updateTeacher(String id, TeacherEntity teacherBody) {
		int teacherId;
		try {
			teacherId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		Optional<TeacherEntity> optionalTeacher = teacherRepository.findById(teacherId);
		if (optionalTeacher.isPresent()) {
			TeacherEntity teacher = optionalTeacher.get();
			teacher.setFirstName(teacherBody.getFirstName());
			teacher.setLastName(teacherBody.getLastName());
			teacherRepository.save(teacher);
			return teacher;
		} else {
			throw new NotFoundException("Teacher", id);
		}
	}

	@Override
	public TeacherEntity deleteTeacher(String id) {
		int teacherId;
		try {
			teacherId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		Optional<TeacherEntity> optionalTeacher = teacherRepository.findById(teacherId);
		if (optionalTeacher.isPresent()) {
			TeacherEntity teacher = optionalTeacher.get();
			teacherRepository.delete(teacher);
			return teacher;
		} else {
			throw new NotFoundException("Teacher", id);
		}
	}
}
