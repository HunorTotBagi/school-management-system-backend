package com.electric_diary.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.ParentEntity;
import com.electric_diary.repositories.ParentRepository;
import com.electric_diary.services.ParentService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ParentServiceImpl extends ErrorMessagesServiceImpl implements ParentService {

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected ParentRepository parentRepository;

	@Override
	public ResponseEntity<?> createParent(ParentEntity parentBody, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		ParentEntity parent = new ParentEntity();
		parent.setFirstName(parentBody.getFirstName());
		parent.setLastName(parentBody.getLastName());
		parent.setEmail(parentBody.getEmail());
		parentRepository.save(parent);

		return new ResponseEntity<>(parent, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllParents() {
		Iterable<ParentEntity> parents = parentRepository.findAll();
		return new ResponseEntity<>(parents, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getParentById(String id) {
		try {
			int parentId = Integer.parseInt(id);
			Optional<ParentEntity> parentOptional = parentRepository.findById(parentId);

			if (parentOptional.isPresent()) {
				return new ResponseEntity<>(parentOptional.get(), HttpStatus.OK);
			} else {
				return createNotFoundResponse("Parent", parentId);
			}
		} catch (NumberFormatException e) {
			return createBadRequestResponse("Invalid ID format");
		} catch (Exception e) {
			return createErrorResponse(e);
		}
	}

	@Override
	public ResponseEntity<?> updateParent(String id, ParentEntity parentBody) {
		try {
			int parentId = Integer.parseInt(id);
			Optional<ParentEntity> optionalParent = parentRepository.findById(parentId);

			if (optionalParent.isPresent()) {
				ParentEntity parent = optionalParent.get();
				parent.setFirstName(parentBody.getFirstName());
				parent.setLastName(parentBody.getLastName());
				parent.setEmail(parentBody.getEmail());
				parentRepository.save(parent);
				return new ResponseEntity<>(parent, HttpStatus.OK);
			} else {
				return createNotFoundResponse("Parent", parentId);
			}
		} catch (NumberFormatException e) {
			return createBadRequestResponse("Invalid ID format");
		} catch (Exception e) {
			return createErrorResponse(e);
		}
	}

	@Override
	public ResponseEntity<?> deleteParent(String id) {
		try {
			int parentId = Integer.parseInt(id);
			Optional<ParentEntity> optionalParent = parentRepository.findById(parentId);

			if (optionalParent.isPresent()) {
				ParentEntity parent = optionalParent.get();
				parentRepository.delete(parent);
				return new ResponseEntity<>(parent, HttpStatus.OK);
			} else {
				return createNotFoundResponse("Parent", parentId);
			}
		} catch (NumberFormatException e) {
			return createBadRequestResponse("Invalid ID format");
		} catch (Exception e) {
			return createErrorResponse(e);
		}
	}
}
