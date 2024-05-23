package com.electric_diary.services;

import com.electric_diary.entities.ParentEntity;

public interface ParentService {
	public ParentEntity createParent(String firstName, String lastName, String email);

	public Iterable<ParentEntity> getAllParents();

	public ParentEntity getParentById(String id);

	public ParentEntity updateParent(String id, String firstName, String lastName, String email);

	public ParentEntity deleteParent(String id);
}
