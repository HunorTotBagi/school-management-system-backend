package com.electric_diary.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.ParentEntity;

public interface ParentService {
	public ResponseEntity<ParentEntity> createParent(ParentEntity parentBody, BindingResult result);

	public ResponseEntity<Iterable<ParentEntity>> getAllParents();

	public ResponseEntity<ParentEntity> getParentById(String id);

	public ResponseEntity<?> updateParent(String id, ParentEntity parentBody);

	public ResponseEntity<?> deleteParent(String id);
}
