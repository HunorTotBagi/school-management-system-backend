package com.electric_diary.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.StudentEntity;
import com.electric_diary.exception.CustomBadRequestException;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.StudentRepository;
import com.electric_diary.services.StudentService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class StudentServiceImpl implements StudentService {

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected StudentRepository studentRepository;

	@Override
	public ResponseEntity<StudentEntity> createStudent(StudentEntity studentBody, BindingResult result) {
		if (result.hasErrors())
			throw new CustomBadRequestException(result);

		StudentEntity student = new StudentEntity();
		student.setFirstName(studentBody.getFirstName());
		student.setLastName(studentBody.getLastName());
		student.setNewClass(studentBody.getNewClass());
		studentRepository.save(student);

		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Iterable<StudentEntity>> getAllStudents() {
		Iterable<StudentEntity> students = studentRepository.findAll();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<StudentEntity> getStudentById(String id) {
		try {
			int studentId = Integer.parseInt(id);
			StudentEntity studentEntity = studentRepository.findById(studentId)
					.orElseThrow(() -> new NotFoundException("Student", id));
			return ResponseEntity.ok(studentEntity);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
	}

	@Override
	public ResponseEntity<StudentEntity> updateStudent(String id, StudentEntity studentBody) {
		int studentId;
		try {
			studentId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		Optional<StudentEntity> optionalStudent = studentRepository.findById(studentId);
		if (optionalStudent.isPresent()) {
			StudentEntity student = optionalStudent.get();
			student.setFirstName(studentBody.getFirstName());
			student.setLastName(studentBody.getLastName());
			student.setNewClass(studentBody.getNewClass());
			studentRepository.save(student);
			return new ResponseEntity<>(student, HttpStatus.OK);
		} else {
			throw new NotFoundException("Student", id);
		}
	}

	@Override
	public ResponseEntity<StudentEntity> deleteStudent(String id) {
		int studentId;
		try {
			studentId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		Optional<StudentEntity> optionalStudent = studentRepository.findById(studentId);
		if (optionalStudent.isPresent()) {
			StudentEntity student = optionalStudent.get();
			studentRepository.delete(student);
			return new ResponseEntity<>(student, HttpStatus.OK);
		} else {
			throw new NotFoundException("Student", id);
		}
	}
}
