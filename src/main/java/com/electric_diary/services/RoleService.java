package com.electric_diary.services;

import com.electric_diary.entities.RoleEntity;

public interface RoleService {
	public RoleEntity createRole(RoleEntity roleBody);

	public Iterable<RoleEntity> getAllRoles();

	public RoleEntity getRoleById(String id);

	public RoleEntity updateRole(String id, RoleEntity roleBody);

	public RoleEntity deleteRole(String id);
}
