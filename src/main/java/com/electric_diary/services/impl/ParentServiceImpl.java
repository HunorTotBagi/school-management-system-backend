package com.electric_diary.services.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.electric_diary.entities.ParentEntity;
import com.electric_diary.entities.StudentEntity;
import com.electric_diary.exception.CustomBadRequestException;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.ParentRepository;
import com.electric_diary.repositories.StudentRepository;
import com.electric_diary.services.ParentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ParentServiceImpl implements ParentService {
	@PersistenceContext
	protected EntityManager entityManager;

	private final ParentRepository parentRepository;
	private final StudentRepository studentRepository;

	public ParentServiceImpl(final ParentRepository parentRepository, final StudentRepository studentRepository) {
		this.parentRepository = parentRepository;
		this.studentRepository = studentRepository;
	}

	@Override
	public ParentEntity createParent(ParentEntity parentBody, BindingResult result) {
		if (result.hasErrors())
			throw new CustomBadRequestException(result);

		ParentEntity parent = new ParentEntity();
		parent.setFirstName(parentBody.getFirstName());
		parent.setLastName(parentBody.getLastName());
		parent.setEmail(parentBody.getEmail());
		parentRepository.save(parent);

		return parent;
	}

	@Override
	public Iterable<ParentEntity> getAllParents() {
		return parentRepository.findAll();
	}

	@Override
	public ParentEntity getParentById(String id) {
		try {
			int parentId = Integer.parseInt(id);
			ParentEntity parent = parentRepository.findById(parentId)
					.orElseThrow(() -> new NotFoundException("Parent", id));
			return parent;
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
	}

	@Override
	public ParentEntity updateParent(String id, ParentEntity parentBody) {
		int parentId;
		try {
			parentId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		Optional<ParentEntity> optionalParent = parentRepository.findById(parentId);
		if (optionalParent.isPresent()) {
			ParentEntity parent = optionalParent.get();
			parent.setFirstName(parentBody.getFirstName());
			parent.setLastName(parentBody.getLastName());
			parent.setEmail(parentBody.getEmail());
			parentRepository.save(parent);
			return parent;
		} else {
			throw new NotFoundException("Parent", id);
		}
	}

	@Override
	public ParentEntity deleteParent(String id) {
		int parentId;
		try {
			parentId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		Optional<ParentEntity> optionalParent = parentRepository.findById(parentId);
		if (optionalParent.isPresent()) {
			ParentEntity parent = optionalParent.get();
			parentRepository.delete(parent);
			return parent;
		} else {
			throw new NotFoundException("Parent", id);
		}
	}

	@Override
	public ParentEntity assignStudentToParent(String parentId, String studentId) {
		int parentInt = Integer.parseInt(parentId);
		ParentEntity parent = parentRepository.findById(parentInt)
				.orElseThrow(() -> new NotFoundException("Parent", parentId));

		int studentInt = Integer.parseInt(studentId);
		StudentEntity student = studentRepository.findById(studentInt)
				.orElseThrow(() -> new NotFoundException("Student", studentId));

		student.setParent(parent);
		studentRepository.save(student);
		return parent;
	}
}
