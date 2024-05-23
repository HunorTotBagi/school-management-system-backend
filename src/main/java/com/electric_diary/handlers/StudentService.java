package com.electric_diary.handlers;

import com.electric_diary.entities.StudentEntity;

public interface StudentService {
	public StudentEntity createStudent(String firstName, String lastName);

	public Iterable<StudentEntity> getAllStudents();

	public StudentEntity getStudentById(String id);

	public StudentEntity updateStudent(String id, String firstName, String lastName);

	public StudentEntity deleteStudent(String id);
}
