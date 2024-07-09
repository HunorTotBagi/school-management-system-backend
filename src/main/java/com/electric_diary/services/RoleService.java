package com.electric_diary.services;

import org.springframework.validation.BindingResult;

import com.electric_diary.entities.RoleEntity;

public interface RoleService {
	public RoleEntity createRole(RoleEntity roleBody, BindingResult result);

	public Iterable<RoleEntity> getAllRoles();

	public RoleEntity getRoleById(String id);

	public RoleEntity updateRole(String id, RoleEntity roleBody);

	public RoleEntity deleteRole(String id);
}
