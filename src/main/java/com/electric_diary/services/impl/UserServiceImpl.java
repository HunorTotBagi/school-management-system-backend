package com.electric_diary.services.impl;

import org.springframework.stereotype.Service;

import com.electric_diary.DTO.Request.UserRequestDTO;
import com.electric_diary.entities.RoleEntity;
import com.electric_diary.entities.UserEntity;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.RoleRepository;
import com.electric_diary.repositories.UserRepository;
import com.electric_diary.services.UserService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class UserServiceImpl implements UserService {
	@PersistenceContext
	protected EntityManager entityManager;

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public UserServiceImpl(final UserRepository userRepository, final RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserEntity createUser(UserRequestDTO userDTOBody) {
		RoleEntity role = fetchRoleById(userDTOBody.getRoleId());

		UserEntity user = new UserEntity();
		user.setName(userDTOBody.getName());
		user.setLastName(userDTOBody.getLastName());
		user.setPassword("{noop}" + userDTOBody.getPassword());
		user.setEmail(userDTOBody.getEmail());
		user.setRole(role);
		userRepository.save(user);

		return user;
	}

	@Override
	public Iterable<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity getUserById(Integer userId) {
		return fetchUserById(userId);
	}

	@Override
	public UserEntity updateUser(Integer userId, UserRequestDTO userDTOBody) {
		UserEntity user = fetchUserById(userId);
		RoleEntity role = fetchRoleById(userDTOBody.getRoleId());

		user.setName(userDTOBody.getName());
		user.setLastName(userDTOBody.getLastName());
		user.setPassword(userDTOBody.getPassword());
		user.setEmail(userDTOBody.getEmail());
		user.setRole(role);
		userRepository.save(user);

		return user;
	}

	@Override
	public UserEntity deleteUser(Integer userId) {
		UserEntity user = fetchUserById(userId);
		userRepository.delete(user);
		return user;
	}

	private UserEntity fetchUserById(Integer userId) {
		return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User", userId));
	}

	private RoleEntity fetchRoleById(Integer roleId) {
		return roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role", roleId));
	}
}
