package com.electric_diary.services;

import org.springframework.validation.BindingResult;

import com.electric_diary.entities.ParentEntity;

public interface ParentService {
	public ParentEntity createParent(ParentEntity parentBody, BindingResult result);

	public Iterable<ParentEntity> getAllParents();

	public ParentEntity getParentById(String id);

	public ParentEntity updateParent(String id, ParentEntity parentBody);

	public ParentEntity deleteParent(String id);
}
