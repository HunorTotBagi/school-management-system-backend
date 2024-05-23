package com.electric_diary.services;

import com.electric_diary.entities.StudentEntity;

public interface StudentService {
	public StudentEntity createStudent(StudentEntity studentBody);

	public Iterable<StudentEntity> getAllStudents();

	public StudentEntity getStudentById(String id);

	public StudentEntity updateStudent(String id, StudentEntity studentBody);

	public StudentEntity deleteStudent(String id);
}
