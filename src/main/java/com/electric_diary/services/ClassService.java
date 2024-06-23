package com.electric_diary.services;

import org.springframework.validation.BindingResult;

import com.electric_diary.entities.ClassEntity;

public interface ClassService {
	public ClassEntity createClass(ClassEntity classBody, BindingResult result);

	public Iterable<ClassEntity> getAllClasses();

	public ClassEntity getClassById(String id);

	public ClassEntity updateClass(String id, ClassEntity classBody);

	public ClassEntity deleteClass(String id);
}
