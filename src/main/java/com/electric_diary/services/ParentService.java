package com.electric_diary.services;

import org.springframework.http.ResponseEntity;

import com.electric_diary.entities.ParentEntity;

public interface ParentService {
	public ParentEntity createParent(ParentEntity parentBody);

	public Iterable<ParentEntity> getAllParents();

	public ResponseEntity<?> getParentById(String id);

	public ParentEntity updateParent(String id, ParentEntity parentBody);

	public ParentEntity deleteParent(String id);
}
