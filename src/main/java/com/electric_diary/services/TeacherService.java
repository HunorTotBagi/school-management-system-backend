package com.electric_diary.services;

import com.electric_diary.entities.TeacherEntity;

public interface TeacherService {
	public TeacherEntity createTeacher(TeacherEntity teacherBody);

	public Iterable<TeacherEntity> getAllTeachers();

	public TeacherEntity getTeacherById(String id);

	public TeacherEntity updateTeacher(String id, TeacherEntity teacherBody);

	public TeacherEntity deleteTeacher(String id);
}
