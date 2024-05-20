package com.electric_diary.handlers;

import com.electric_diary.entities.TeacherEntity;

public interface TeacherHandler {
	public Iterable<TeacherEntity> getAllTeachers();
	
	public TeacherEntity createTeacher(String firstName, String lastName);
	
	public TeacherEntity getTeacherById(String id);
	
	public TeacherEntity updateTeacher(String id, String firstName, String lastName);
	
	public TeacherEntity deleteTeacher(String id);
}
