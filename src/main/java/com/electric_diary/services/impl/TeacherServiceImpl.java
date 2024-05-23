package com.electric_diary.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public TeacherEntity createTeacher(TeacherEntity teacherBody) {
		TeacherEntity teacher = new TeacherEntity();
		teacher.setFirstName(teacherBody.getFirstName());
		teacher.setLastName(teacherBody.getLastName());
		teacherRepository.save(teacher);
		return teacher;
	}

	@Override
	public Iterable<TeacherEntity> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public TeacherEntity getTeacherById(String id) {
		return teacherRepository.findById(Integer.parseInt(id)).get();
	}

	@Override
	public TeacherEntity updateTeacher(String id, TeacherEntity teacherBody) {
		TeacherEntity teacher = teacherRepository.findById(Integer.parseInt(id)).get();
		if (teacher != null) {
			teacher.setFirstName(teacherBody.getFirstName());
			teacher.setLastName(teacherBody.getLastName());
			teacherRepository.save(teacher);
			return teacher;
		}
		return new TeacherEntity();
	}

	@Override
	public TeacherEntity deleteTeacher(String id) {
		TeacherEntity teacher = teacherRepository.findById(Integer.parseInt(id)).get();
		if (teacher != null) {
			teacherRepository.delete(teacher);
			return teacher;
		}
		return new TeacherEntity();
	}
}
