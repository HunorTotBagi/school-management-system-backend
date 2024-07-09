package com.electric_diary.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.RoleEntity;
import com.electric_diary.exception.CustomBadRequestException;
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
	public RoleEntity getRoleById(String id) {
		try {
			int roleId = Integer.parseInt(id);
			RoleEntity role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role", id));
			return role;
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
	}

	@Override
	public RoleEntity updateRole(String id, RoleEntity roleBody) {
		int roleId;
		try {
			roleId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		Optional<RoleEntity> optionalRole = roleRepository.findById(roleId);
		if (optionalRole.isPresent()) {
			RoleEntity role = optionalRole.get();
			role.setName(roleBody.getName());
			roleRepository.save(role);
			return role;
		} else {
			throw new NotFoundException("Role", id);
		}
	}

	@Override
	public RoleEntity deleteRole(String id) {
		int roleId;
		try {
			roleId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		Optional<RoleEntity> optionalRole = roleRepository.findById(roleId);
		if (optionalRole.isPresent()) {
			RoleEntity role = optionalRole.get();
			roleRepository.delete(role);
			return role;
		} else {
			throw new NotFoundException("Role", id);
		}
	}
}
