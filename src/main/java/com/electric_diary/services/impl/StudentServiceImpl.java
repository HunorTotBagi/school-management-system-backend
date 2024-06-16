package com.electric_diary.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.StudentEntity;
import com.electric_diary.repositories.StudentRepository;
import com.electric_diary.services.StudentService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class StudentServiceImpl extends ErrorMessagesServiceImpl implements StudentService {

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected StudentRepository studentRepository;

	@Override
	public ResponseEntity<?> createStudent(StudentEntity studentBody, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		StudentEntity student = new StudentEntity();
		student.setFirstName(studentBody.getFirstName());
		student.setLastName(studentBody.getLastName());
		studentRepository.save(student);

		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllStudents() {
		Iterable<StudentEntity> students = studentRepository.findAll();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getStudentById(String id) {
		try {
			int studentId = Integer.parseInt(id);
			Optional<StudentEntity> studentOptional = studentRepository.findById(studentId);

			if (studentOptional.isPresent()) {
				return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);
			} else {
				return createNotFoundResponse("Student", studentId);
			}
		} catch (NumberFormatException e) {
			return createBadRequestResponse("Invalid ID format");
		} catch (Exception e) {
			return createErrorResponse(e);
		}
	}

	@Override
	public ResponseEntity<?> updateStudent(String id, StudentEntity studentBody) {
		try {
			int studentId = Integer.parseInt(id);
			Optional<StudentEntity> optionalStudent = studentRepository.findById(studentId);

			if (optionalStudent.isPresent()) {
				StudentEntity student = optionalStudent.get();
				student.setFirstName(studentBody.getFirstName());
				student.setLastName(studentBody.getLastName());
				studentRepository.save(student);
				return new ResponseEntity<>(student, HttpStatus.OK);
			} else {
				return createNotFoundResponse("Student", studentId);
			}
		} catch (NumberFormatException e) {
			return createBadRequestResponse("Invalid ID format");
		} catch (Exception e) {
			return createErrorResponse(e);
		}
	}

	@Override
	public ResponseEntity<?> deleteStudent(String id) {
		try {
			int studentId = Integer.parseInt(id);
			Optional<StudentEntity> optionalStudent = studentRepository.findById(studentId);

			if (optionalStudent.isPresent()) {
				StudentEntity student = optionalStudent.get();
				studentRepository.delete(student);
				return new ResponseEntity<>(student, HttpStatus.OK);
			} else {
				return createNotFoundResponse("Student", studentId);
			}
		} catch (NumberFormatException e) {
			return createBadRequestResponse("Invalid ID format");
		} catch (Exception e) {
			return createErrorResponse(e);
		}
	}
}
