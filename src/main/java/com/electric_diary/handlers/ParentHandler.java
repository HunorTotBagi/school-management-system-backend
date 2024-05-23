package com.electric_diary.handlers;

import com.electric_diary.entities.ParentEntity;

public interface ParentHandler {
	public ParentEntity createParent(String firstName, String lastName, String email);

	public Iterable<ParentEntity> getAllParents();

	public ParentEntity getParentById(String id);

	public ParentEntity updateParent(String id, String firstName, String lastName, String email);

	public ParentEntity deleteParent(String id);
}
