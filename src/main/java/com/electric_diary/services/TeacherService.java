package com.electric_diary.services;

import com.electric_diary.entities.TeacherEntity;

public interface TeacherService {
	public TeacherEntity createTeacher(String firstName, String lastName);

	public Iterable<TeacherEntity> getAllTeachers();

	public TeacherEntity getTeacherById(String id);

	public TeacherEntity updateTeacher(String id, String firstName, String lastName);

	public TeacherEntity deleteTeacher(String id);
}
