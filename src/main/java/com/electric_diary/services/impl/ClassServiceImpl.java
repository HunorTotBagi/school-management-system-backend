package com.electric_diary.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.ClassEntity;
import com.electric_diary.exception.CustomBadRequestException;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.ClassRepository;
import com.electric_diary.services.ClassService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ClassServiceImpl implements ClassService {

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected ClassRepository classRepository;

	@Override
	public ClassEntity createClass(ClassEntity classBody, BindingResult result) {
		if (result.hasErrors())
			throw new CustomBadRequestException(result);

		ClassEntity newClass = new ClassEntity();
		newClass.setName(classBody.getName());
		classRepository.save(newClass);

		return newClass;
	}

	@Override
	public Iterable<ClassEntity> getAllClasses() {
		return classRepository.findAll();
	}

	@Override
	public ClassEntity getClassById(String id) {
		try {
			int classId = Integer.parseInt(id);
			ClassEntity newClass = classRepository.findById(classId)
					.orElseThrow(() -> new NotFoundException("Class", id));
			return newClass;
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
	}

	@Override
	public ClassEntity updateClass(String id, ClassEntity classBody) {
		int classId;
		try {
			classId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		Optional<ClassEntity> optionalClass = classRepository.findById(classId);
		if (optionalClass.isPresent()) {
			ClassEntity newClass = optionalClass.get();
			newClass.setName(classBody.getName());
			classRepository.save(newClass);
			return newClass;
		} else {
			throw new NotFoundException("Class", id);
		}
	}

	@Override
	public ClassEntity deleteClass(String id) {
		int classId;
		try {
			classId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		Optional<ClassEntity> optionalClass = classRepository.findById(classId);
		if (optionalClass.isPresent()) {
			ClassEntity newClass = optionalClass.get();
			classRepository.delete(newClass);
			return newClass;
		} else {
			throw new NotFoundException("Class", id);
		}
	}
}
