package com.electric_diary.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electric_diary.entities.TeacherEntity;
import com.electric_diary.repositories.TeacherRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TeacherHandlerImplementation implements TeacherHandler {

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected TeacherRepository teacherRepository;

	@Override
	public TeacherEntity createTeacher(String firstName, String lastName) {
		TeacherEntity teacher = new TeacherEntity();
		teacher.setFirstName(firstName);
		teacher.setLastName(lastName);
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
	public TeacherEntity updateTeacher(String id, String firstName, String lastName) {
		TeacherEntity teacher = teacherRepository.findById(Integer.parseInt(id)).get();
		if (teacher != null) {
			teacher.setFirstName(firstName);
			teacher.setLastName(lastName);
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
