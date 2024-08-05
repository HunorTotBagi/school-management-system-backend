package com.electric_diary.services;

import com.electric_diary.DTO.TeacherDTO;
import com.electric_diary.entities.TeacherEntity;

public interface TeacherService {
	public TeacherEntity createTeacher(TeacherDTO teacherDTOBody);

	public Iterable<TeacherEntity> getAllTeachers();

	public TeacherEntity getTeacherById(String id);

	public TeacherEntity updateTeacher(String id, TeacherDTO teacherDTOBody);

	public TeacherEntity deleteTeacher(String id);

	public TeacherEntity teacherTeachesSubject(TeacherDTO TeacherDTOBody);
}
