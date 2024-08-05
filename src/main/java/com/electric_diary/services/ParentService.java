package com.electric_diary.services;

import com.electric_diary.DTO.ParentDTO;
import com.electric_diary.entities.ParentEntity;

public interface ParentService {
	public ParentEntity createParent(ParentDTO parentDTOBody);

	public Iterable<ParentEntity> getAllParents();

	public ParentEntity getParentById(String id);

	public ParentEntity updateParent(String id, ParentDTO parentDTOBody);

	public ParentEntity deleteParent(String id);

	public ParentEntity assignStudentToParent(String parentId, String studentId);
}
