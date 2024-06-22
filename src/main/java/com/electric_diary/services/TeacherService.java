package com.electric_diary.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.TeacherEntity;

public interface TeacherService {
	public ResponseEntity<TeacherEntity> createTeacher(TeacherEntity teacherBody, BindingResult result);

	public ResponseEntity<Iterable<TeacherEntity>> getAllTeachers();

	public ResponseEntity<TeacherEntity> getTeacherById(String id);

	public ResponseEntity<TeacherEntity> updateTeacher(String id, TeacherEntity teacherBody);

	public ResponseEntity<TeacherEntity> deleteTeacher(String id);
}
