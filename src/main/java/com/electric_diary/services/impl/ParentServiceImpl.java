package com.electric_diary.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.electric_diary.controllers.util.RESTError;
import com.electric_diary.entities.ParentEntity;
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
	public ParentEntity createParent(ParentEntity parentBody) {
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
	public ResponseEntity<?> getParentById(String id) {
		try {
			ParentEntity parent = parentRepository.findById(Integer.parseInt(id)).get();
			if (parent != null)
				return new ResponseEntity<ParentEntity>(parent, HttpStatus.OK);
			return new ResponseEntity<RESTError>(new RESTError(1, "Parent not found"), HttpStatus.NOT_FOUND);
		} catch (NumberFormatException e) {
			return new ResponseEntity<>("Invalid ID format", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ParentEntity updateParent(String id, ParentEntity parentBody) {
		ParentEntity parent = parentRepository.findById(Integer.parseInt(id)).get();
		if (parent != null) {
			parent.setFirstName(parentBody.getFirstName());
			parent.setLastName(parentBody.getLastName());
			parent.setEmail(parentBody.getEmail());
			parentRepository.save(parent);
			return parent;
		}
		return new ParentEntity();
	}

	@Override
	public ParentEntity deleteParent(String id) {
		ParentEntity student = parentRepository.findById(Integer.parseInt(id)).get();
		if (student != null) {
			parentRepository.delete(student);
			return student;
		}
		return new ParentEntity();
	}
}
