package com.electric_diary.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.TeacherEntity;
import com.electric_diary.repositories.TeacherRepository;
import com.electric_diary.services.TeacherService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TeacherServiceImpl extends ErrorMessagesServiceImpl implements TeacherService {

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
	public ResponseEntity<?> getAllTeachers() {
		Iterable<TeacherEntity> teachers = teacherRepository.findAll();
		return new ResponseEntity<>(teachers, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getTeacherById(String id) {
		try {
			int teacherId = Integer.parseInt(id);
			Optional<TeacherEntity> teacherOptional = teacherRepository.findById(teacherId);

			if (teacherOptional.isPresent()) {
				return new ResponseEntity<>(teacherOptional.get(), HttpStatus.OK);
			} else {
				return createNotFoundResponse("Teacher", teacherId);
			}
		} catch (NumberFormatException e) {
			return createBadRequestResponse("Invalid ID format");
		} catch (Exception e) {
			return createErrorResponse(e);
		}
	}

	@Override
	public ResponseEntity<?> updateTeacher(String id, TeacherEntity teacherBody) {
		try {
			int teacherId = Integer.parseInt(id);
			Optional<TeacherEntity> optionalTeacher = teacherRepository.findById(teacherId);

			if (optionalTeacher.isPresent()) {
				TeacherEntity teacher = optionalTeacher.get();
				teacher.setFirstName(teacherBody.getFirstName());
				teacher.setLastName(teacherBody.getLastName());
				teacherRepository.save(teacher);
				return new ResponseEntity<>(teacher, HttpStatus.OK);
			} else {
				return createNotFoundResponse("Teacher", teacherId);
			}
		} catch (NumberFormatException e) {
			return createBadRequestResponse("Invalid ID format");
		} catch (Exception e) {
			return createErrorResponse(e);
		}
	}

	@Override
	public ResponseEntity<?> deleteTeacher(String id) {
		try {
			int teacherId = Integer.parseInt(id);
			Optional<TeacherEntity> optionalTeacher = teacherRepository.findById(teacherId);

			if (optionalTeacher.isPresent()) {
				TeacherEntity teacher = optionalTeacher.get();
				teacherRepository.delete(teacher);
				return new ResponseEntity<>(teacher, HttpStatus.OK);
			} else {
				return createNotFoundResponse("Teacher", teacherId);
			}
		} catch (NumberFormatException e) {
			return createBadRequestResponse("Invalid ID format");
		} catch (Exception e) {
			return createErrorResponse(e);
		}
	}
}
