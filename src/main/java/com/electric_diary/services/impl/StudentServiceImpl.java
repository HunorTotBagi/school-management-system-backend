package com.electric_diary.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.electric_diary.DTO.StudentDTO;
import com.electric_diary.entities.ClassEntity;
import com.electric_diary.entities.ParentEntity;
import com.electric_diary.entities.StudentEntity;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.ClassRepository;
import com.electric_diary.repositories.ParentRepository;
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

	@Autowired
	protected ClassRepository classRepository;

	@Autowired
	protected ParentRepository parentRepository;

	@Override
	public StudentEntity createStudent(StudentDTO studentDTOBody, BindingResult result) {
		String classId = studentDTOBody.getClassId();
		String parentId = studentDTOBody.getParentId();

		ClassEntity newClass = classRepository.findById(Integer.parseInt(classId))
				.orElseThrow(() -> new NotFoundException("Class", classId));
		ParentEntity parent = parentRepository.findById(Integer.parseInt(parentId))
				.orElseThrow(() -> new NotFoundException("Parent", parentId));

		StudentEntity student = new StudentEntity();
		student.setFirstName(studentDTOBody.getFirstName());
		student.setLastName(studentDTOBody.getLastName());

		student.setNewClass(newClass);
		student.setParent(parent);
		studentRepository.save(student);

		return student;
	}

	@Override
	public Iterable<StudentEntity> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public StudentEntity getStudentById(String id) {
		try {
			int studentId = Integer.parseInt(id);
			StudentEntity student = studentRepository.findById(studentId)
					.orElseThrow(() -> new NotFoundException("Student", id));
			return student;
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
	}

	@Override
	public StudentEntity updateStudent(String id, StudentEntity studentBody) {
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
			student.setParent(studentBody.getParent());
			studentRepository.save(student);
			return student;
		} else {
			throw new NotFoundException("Student", id);
		}
	}

	@Override
	public StudentEntity deleteStudent(String id) {
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
			return student;
		} else {
			throw new NotFoundException("Student", id);
		}
	}
}
