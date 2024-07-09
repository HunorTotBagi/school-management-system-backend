package com.electric_diary.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.RoleEntity;
import com.electric_diary.exception.CustomBadRequestException;
import com.electric_diary.repositories.RoleRepository;
import com.electric_diary.services.RoleService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class RoleServiceImpl implements RoleService{
	@PersistenceContext
	protected EntityManager entityManager;

	private final RoleRepository roleRepository;
	
	public RoleServiceImpl(final RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public RoleEntity createRole(RoleEntity roleBody, BindingResult result) {
		if (result.hasErrors())
			throw new CustomBadRequestException(result);
		
		RoleEntity role = new RoleEntity();
		role.setName(roleBody.getName());
		roleRepository.save(role);

		return role;
	}

	@Override
	public Iterable<RoleEntity> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public RoleEntity getRoleEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleEntity updateRoleEntity(String id, RoleEntity roleBody) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleEntity deleteRole(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
