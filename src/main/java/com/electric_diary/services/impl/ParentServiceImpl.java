package com.electric_diary.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.electric_diary.DTO.ParentDTO;
import com.electric_diary.entities.ParentEntity;
import com.electric_diary.entities.StudentEntity;
import com.electric_diary.entities.UserEntity;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.ParentRepository;
import com.electric_diary.repositories.StudentRepository;
import com.electric_diary.repositories.UserRepository;
import com.electric_diary.services.ParentService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ParentServiceImpl implements ParentService {
	@PersistenceContext
	protected EntityManager entityManager;

	private final ParentRepository parentRepository;
	private final StudentRepository studentRepository;
	private final UserRepository userRepository;

	public ParentServiceImpl(final ParentRepository parentRepository, final StudentRepository studentRepository,
			final UserRepository userRepository) {
		this.parentRepository = parentRepository;
		this.studentRepository = studentRepository;
		this.userRepository = userRepository;
	}

	@Override
	public ParentEntity createParent(ParentDTO parentDTOBody) {
		String userId = parentDTOBody.getUserId();

		UserEntity user = userRepository.findById(Integer.parseInt(userId))
				.orElseThrow(() -> new NotFoundException("User", userId));

		ParentEntity parent = new ParentEntity();
		parent.setFirstName(parentDTOBody.getFirstName());
		parent.setLastName(parentDTOBody.getLastName());
		parent.setEmail(parentDTOBody.getEmail());
		parent.setUser(user);
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
	public ParentEntity updateParent(String id, ParentDTO parentDTOBody) {
		int parentId;
		try {
			parentId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		Optional<ParentEntity> optionalParent = parentRepository.findById(parentId);

		if (!optionalParent.isPresent())
			throw new NotFoundException("Parent", id);

		String userId = parentDTOBody.getUserId();

		UserEntity user = userRepository.findById(Integer.parseInt(userId))
				.orElseThrow(() -> new NotFoundException("User", userId));

		ParentEntity parent = optionalParent.get();
		parent.setFirstName(parentDTOBody.getFirstName());
		parent.setLastName(parentDTOBody.getLastName());
		parent.setEmail(parentDTOBody.getEmail());
		parent.setUser(user);
		parentRepository.save(parent);
		return parent;
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

		if (!optionalParent.isPresent())
			throw new NotFoundException("Parent", id);

		ParentEntity parent = optionalParent.get();
		parentRepository.delete(parent);
		return parent;
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
