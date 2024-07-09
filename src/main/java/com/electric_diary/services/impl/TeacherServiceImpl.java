package com.electric_diary.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.electric_diary.DTO.TeacherDTO;
import com.electric_diary.entities.ClassEntity;
import com.electric_diary.entities.SubjectEntity;
import com.electric_diary.entities.TeacherEntity;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.ClassRepository;
import com.electric_diary.repositories.SubjectRepository;
import com.electric_diary.repositories.TeacherRepository;
import com.electric_diary.services.TeacherService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TeacherServiceImpl implements TeacherService {
	@PersistenceContext
	protected EntityManager em;

	private final TeacherRepository teacherRepository;
	private final SubjectRepository subjectRepository;
	private final ClassRepository classRepository;

	public TeacherServiceImpl(final TeacherRepository teacherRepository, final SubjectRepository subjectRepository,
			final ClassRepository classRepository) {
		this.teacherRepository = teacherRepository;
		this.subjectRepository = subjectRepository;
		this.classRepository = classRepository;
	}

	@Override
	public TeacherEntity createTeacher(TeacherDTO teacherDTOBody, BindingResult result) {
		String subjectId = teacherDTOBody.getSubjectId();
		String classId = teacherDTOBody.getClassId();

		SubjectEntity subject = subjectRepository.findById(Integer.parseInt(subjectId))
				.orElseThrow(() -> new NotFoundException("Subject", subjectId));
		ClassEntity newClass = classRepository.findById(Integer.parseInt(classId))
				.orElseThrow(() -> new NotFoundException("Class", classId));

		TeacherEntity teacher = new TeacherEntity();
		teacher.setFirstName(teacherDTOBody.getFirstName());
		teacher.setLastName(teacherDTOBody.getLastName());
		teacher.setSubject(subject);
		teacher.setNewClass(newClass);
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
	public TeacherEntity updateTeacher(String id, TeacherEntity teacherBody) {
		int teacherId;
		try {
			teacherId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		TeacherEntity teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new NotFoundException("Teacher", id));
		teacher.setFirstName(teacherBody.getFirstName());
		teacher.setLastName(teacherBody.getLastName());
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
	public TeacherEntity teacherTeachesSubjectToClass(TeacherDTO teacherDTOBody) {
		String teacherId = teacherDTOBody.getTeacherId();
		String subjectId = teacherDTOBody.getSubjectId();
		String classId = teacherDTOBody.getClassId();

		SubjectEntity subject = subjectRepository.findById(Integer.parseInt(subjectId))
				.orElseThrow(() -> new NotFoundException("Subject", subjectId));
		ClassEntity newClass = classRepository.findById(Integer.parseInt(classId))
				.orElseThrow(() -> new NotFoundException("Class", classId));

		TeacherEntity teacher = teacherRepository.findById(Integer.parseInt(teacherId))
				.orElseThrow(() -> new NotFoundException("Teacher", teacherId));
		teacher.setSubject(subject);
		teacher.setNewClass(newClass);
		teacherRepository.save(teacher);
		return teacher;
	}
}
