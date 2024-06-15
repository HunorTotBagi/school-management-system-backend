package com.electric_diary.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.TeacherEntity;

public interface TeacherService {
	public ResponseEntity<?> createTeacher(TeacherEntity teacherBody, BindingResult result);

	public ResponseEntity<?> getAllTeachers();

	public ResponseEntity<?> getTeacherById(String id);

	public ResponseEntity<?> updateTeacher(String id, TeacherEntity teacherBody);

	public ResponseEntity<?> deleteTeacher(String id);
}
