package com.electric_diary.services.impl;

import org.springframework.stereotype.Service;

import com.electric_diary.DTO.Request.ClassRequestDTO;
import com.electric_diary.entities.ClassEntity;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.ClassRepository;
import com.electric_diary.services.ClassService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ClassServiceImpl implements ClassService {
	@PersistenceContext
	protected EntityManager entityManager;

	private final ClassRepository classRepository;

	public ClassServiceImpl(final ClassRepository classRepository) {
		this.classRepository = classRepository;
	}

	@Override
	public ClassEntity createClass(ClassRequestDTO classRequestDTO) {
		ClassEntity newClass = new ClassEntity();
		newClass.setName(classRequestDTO.getName());
		classRepository.save(newClass);

		return newClass;
	}

	@Override
	public Iterable<ClassEntity> getAllClasses() {
		return classRepository.findAll();
	}

	@Override
	public ClassEntity getClassById(Integer classId) {
		return classRepository.findById(classId).orElseThrow(() -> new NotFoundException("Class", classId));
	}

	@Override
	public ClassEntity updateClass(Integer classId, ClassRequestDTO classRequestDTO) {
		ClassEntity newClass = getClassById(classId);

		newClass.setName(classRequestDTO.getName());
		classRepository.save(newClass);

		return newClass;
	}

	@Override
	public ClassEntity deleteClass(Integer classId) {
		ClassEntity newClass = getClassById(classId);
		classRepository.delete(newClass);
		return newClass;
	}
}
