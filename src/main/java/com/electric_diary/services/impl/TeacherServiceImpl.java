package com.electric_diary.services.impl;

import org.springframework.stereotype.Service;

import com.electric_diary.DTO.TeacherDTO;
import com.electric_diary.entities.ClassEntity;
import com.electric_diary.entities.SubjectEntity;
import com.electric_diary.entities.TeacherEntity;
import com.electric_diary.entities.UserEntity;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.ClassRepository;
import com.electric_diary.repositories.SubjectRepository;
import com.electric_diary.repositories.TeacherRepository;
import com.electric_diary.repositories.UserRepository;
import com.electric_diary.services.TeacherService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TeacherServiceImpl implements TeacherService {
	@PersistenceContext
	protected EntityManager entityManager;

	private final TeacherRepository teacherRepository;
	private final SubjectRepository subjectRepository;
	private final ClassRepository classRepository;
	private final UserRepository userRepository;

	public TeacherServiceImpl(final TeacherRepository teacherRepository, final SubjectRepository subjectRepository,
			final ClassRepository classRepository, final UserRepository userRepository) {
		this.teacherRepository = teacherRepository;
		this.subjectRepository = subjectRepository;
		this.classRepository = classRepository;
		this.userRepository = userRepository;
	}

	@Override
	public TeacherEntity createTeacher(TeacherDTO teacherDTOBody) {
		String classId = teacherDTOBody.getClassId();
		String userId = teacherDTOBody.getUserId();

		ClassEntity newClass = classRepository.findById(Integer.parseInt(classId))
				.orElseThrow(() -> new NotFoundException("Class", classId));

		UserEntity user = userRepository.findById(Integer.parseInt(userId))
				.orElseThrow(() -> new NotFoundException("User", userId));

		TeacherEntity teacher = new TeacherEntity();
		teacher.setFirstName(teacherDTOBody.getFirstName());
		teacher.setLastName(teacherDTOBody.getLastName());
		teacher.setNewClass(newClass);
		teacher.setUser(user);

		teacherRepository.save(teacher);
		return teacher;
	}

	@Override
	public Iterable<TeacherEntity> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public TeacherEntity getTeacherById(String id) {
		try {
			int teacherId = Integer.parseInt(id);
			return teacherRepository.findById(teacherId).orElseThrow(() -> new NotFoundException("Teacher", id));
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
	}

	@Override
	public TeacherEntity updateTeacher(String id, TeacherDTO teacherDTOBody) {
		int teacherId;
		try {
			teacherId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		TeacherEntity teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new NotFoundException("Teacher", id));

		String userId = teacherDTOBody.getUserId();

		UserEntity user = userRepository.findById(Integer.parseInt(userId))
				.orElseThrow(() -> new NotFoundException("User", userId));

		teacher.setFirstName(teacherDTOBody.getFirstName());
		teacher.setLastName(teacherDTOBody.getLastName());
		teacher.setUser(user);
		teacherRepository.save(teacher);
		return teacher;
	}

	@Override
	public TeacherEntity deleteTeacher(String id) {
		int teacherId;
		try {
			teacherId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		TeacherEntity teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new NotFoundException("Teacher", id));

		teacherRepository.delete(teacher);
		return teacher;
	}

	@Override
	public TeacherEntity teacherTeachesSubject(TeacherDTO teacherDTOBody) {
		String teacherId = teacherDTOBody.getTeacherId();
		String subjectId = teacherDTOBody.getSubjectId();

		SubjectEntity subject = subjectRepository.findById(Integer.parseInt(subjectId))
				.orElseThrow(() -> new NotFoundException("Subject", subjectId));

		TeacherEntity teacher = teacherRepository.findById(Integer.parseInt(teacherId))
				.orElseThrow(() -> new NotFoundException("Teacher", teacherId));

		teacher.addSubjects(subject);
		teacherRepository.save(teacher);
		return teacher;
	}

}
