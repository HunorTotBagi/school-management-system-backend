package com.electric_diary.services.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.electric_diary.controllers.util.RESTError;
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

	private ResponseEntity<RESTError> createNotFoundResponse(String entityName, int entityId) {
		return new ResponseEntity<>(new RESTError(1, String.format("%s with ID %s not found", entityName, entityId)),
				HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<RESTError> createErrorResponse(Exception e) {
		return new ResponseEntity<>(new RESTError(2, "Exception occurred: " + e.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<RESTError> createBadRequestResponse(String message) {
		return new ResponseEntity<>(new RESTError(3, message), HttpStatus.BAD_REQUEST);
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}
}
