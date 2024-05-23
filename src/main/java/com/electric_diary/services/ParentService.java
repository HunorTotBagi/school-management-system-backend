package com.electric_diary.services;

import com.electric_diary.entities.ParentEntity;

public interface ParentService {
	public ParentEntity createParent(ParentEntity parentBody);

	public Iterable<ParentEntity> getAllParents();

	public ParentEntity getParentById(String id);

	public ParentEntity updateParent(String id, ParentEntity parentBody);

	public ParentEntity deleteParent(String id);
}
