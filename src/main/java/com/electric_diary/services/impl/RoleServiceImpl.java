package com.electric_diary.services.impl;

import org.springframework.stereotype.Service;

import com.electric_diary.DTO.Request.RoleRequestDTO;
import com.electric_diary.entities.RoleEntity;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.RoleRepository;
import com.electric_diary.services.RoleService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class RoleServiceImpl implements RoleService {
	@PersistenceContext
	protected EntityManager entityManager;

	private final RoleRepository roleRepository;

	public RoleServiceImpl(final RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public RoleEntity createRole(RoleRequestDTO roleRequestDTO) {
		RoleEntity role = new RoleEntity();
		role.setName(roleRequestDTO.getName());
		roleRepository.save(role);
		return role;
	}

	@Override
	public Iterable<RoleEntity> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public RoleEntity getRoleById(Integer roleId) {
		return roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role", roleId));
	}

	@Override
	public RoleEntity updateRole(Integer roleId, RoleRequestDTO roleRequestDTO) {
		RoleEntity role = getRoleById(roleId);

		role.setName(roleRequestDTO.getName());
		roleRepository.save(role);

		return role;
	}

	@Override
	public RoleEntity deleteRole(Integer roleId) {
		RoleEntity role = getRoleById(roleId);
		roleRepository.delete(role);
		return role;
	}
}
