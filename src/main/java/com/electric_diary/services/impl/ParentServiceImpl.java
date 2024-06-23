package com.electric_diary.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.ParentEntity;
import com.electric_diary.exception.CustomBadRequestException;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.ParentRepository;
import com.electric_diary.services.ParentService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ParentServiceImpl implements ParentService {

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected ParentRepository parentRepository;

	@Override
	public ResponseEntity<ParentEntity> createParent(ParentEntity parentBody, BindingResult result) {
		if (result.hasErrors())
			throw new CustomBadRequestException(result);

		ParentEntity parent = new ParentEntity();
		parent.setFirstName(parentBody.getFirstName());
		parent.setLastName(parentBody.getLastName());
		parent.setEmail(parentBody.getEmail());
		parentRepository.save(parent);

		return new ResponseEntity<ParentEntity>(parent, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Iterable<ParentEntity>> getAllParents() {
		Iterable<ParentEntity> parents = parentRepository.findAll();
		return new ResponseEntity<>(parents, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ParentEntity> getParentById(String id) {
		try {
			int parentId = Integer.parseInt(id);
			ParentEntity parentEntity = parentRepository.findById(parentId)
					.orElseThrow(() -> new NotFoundException("Parent", id));
			return ResponseEntity.ok(parentEntity);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
	}

	@Override
	public ResponseEntity<ParentEntity> updateParent(String id, ParentEntity parentBody) {
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
			return new ResponseEntity<>(parent, HttpStatus.OK);
		} else {
			throw new NotFoundException("Parent", id);
		}
	}

	@Override
	public ResponseEntity<ParentEntity> deleteParent(String id) {
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
			return new ResponseEntity<>(parent, HttpStatus.OK);
		} else {
			throw new NotFoundException("Parent", id);
		}
	}
}
