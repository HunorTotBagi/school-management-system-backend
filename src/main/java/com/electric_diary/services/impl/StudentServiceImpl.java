package com.electric_diary.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

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
	protected EntityManager entityManager;

	private final StudentRepository studentRepository;
	private final ClassRepository classRepository;
	private final ParentRepository parentRepository;

	public StudentServiceImpl(final StudentRepository studentRepository, final ClassRepository classRepository,
			final ParentRepository parentRepository) {
		this.studentRepository = studentRepository;
		this.classRepository = classRepository;
		this.parentRepository = parentRepository;
	}

	@Override
	public StudentEntity createStudent(StudentDTO studentDTOBody) {
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
	public StudentEntity updateStudent(String id, StudentDTO studentDTOBody) {
		String classId = studentDTOBody.getClassId();
		String parentId = studentDTOBody.getParentId();

		ClassEntity newClass = classRepository.findById(Integer.parseInt(classId))
				.orElseThrow(() -> new NotFoundException("Class", classId));
		ParentEntity parent = parentRepository.findById(Integer.parseInt(parentId))
				.orElseThrow(() -> new NotFoundException("Parent", parentId));

		Optional<StudentEntity> optionalStudent = studentRepository.findById(Integer.parseInt(id));

		if (!optionalStudent.isPresent())
			throw new NotFoundException("Student", id);

		StudentEntity student = optionalStudent.get();
		student.setFirstName(studentDTOBody.getFirstName());
		student.setLastName(studentDTOBody.getLastName());
		student.setNewClass(newClass);
		student.setParent(parent);
		studentRepository.save(student);
		return student;
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

		if (!optionalStudent.isPresent())
			throw new NotFoundException("Student", id);

		StudentEntity student = optionalStudent.get();
		studentRepository.delete(student);
		return student;
	}
}
