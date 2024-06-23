package com.electric_diary.services;

import org.springframework.validation.BindingResult;

import com.electric_diary.entities.TeacherEntity;

public interface TeacherService {
	public TeacherEntity createTeacher(TeacherEntity teacherBody, BindingResult result);

	public Iterable<TeacherEntity> getAllTeachers();

	public TeacherEntity getTeacherById(String id);

	public TeacherEntity updateTeacher(String id, TeacherEntity teacherBody);

	public TeacherEntity deleteTeacher(String id);
	
	public TeacherEntity assignSubjectToTeacher(String teacherId, String subjectId);
}
