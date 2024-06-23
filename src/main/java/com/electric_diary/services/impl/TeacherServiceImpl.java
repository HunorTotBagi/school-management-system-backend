package com.electric_diary.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<TeacherEntity> createTeacher(TeacherEntity teacherBody, BindingResult result) {
		if (result.hasErrors())
			throw new CustomBadRequestException(result);

		TeacherEntity teacher = new TeacherEntity();
		teacher.setFirstName(teacherBody.getFirstName());
		teacher.setLastName(teacherBody.getLastName());
		teacherRepository.save(teacher);

		return new ResponseEntity<>(teacher, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Iterable<TeacherEntity>> getAllTeachers() {
		Iterable<TeacherEntity> teachers = teacherRepository.findAll();
		return new ResponseEntity<>(teachers, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TeacherEntity> getTeacherById(String id) {
		try {
			int teacherId = Integer.parseInt(id);
			TeacherEntity teacherEntity = teacherRepository.findById(teacherId)
					.orElseThrow(() -> new NotFoundException("Teacher", id));
			return ResponseEntity.ok(teacherEntity);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
	}

	@Override
	public ResponseEntity<TeacherEntity> updateTeacher(String id, TeacherEntity teacherBody) {
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
			return new ResponseEntity<>(teacher, HttpStatus.OK);
		} else {
			throw new NotFoundException("Teacher", id);
		}
	}

	@Override
	public ResponseEntity<TeacherEntity> deleteTeacher(String id) {
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
			return new ResponseEntity<>(teacher, HttpStatus.OK);
		} else {
			throw new NotFoundException("Teacher", id);
		}
	}
}
