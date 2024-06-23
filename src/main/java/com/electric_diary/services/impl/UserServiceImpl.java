package com.electric_diary.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.UserEntity;
import com.electric_diary.exception.CustomBadRequestException;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.UserRepository;
import com.electric_diary.services.UserService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class UserServiceImpl implements UserService {

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected UserRepository userRepository;

	@Override
	public ResponseEntity<UserEntity> createUser(UserEntity userBody, BindingResult result) {
		if (result.hasErrors())
			throw new CustomBadRequestException(result);
		
		UserEntity user = new UserEntity();
		user.setUsername(userBody.getUsername());
		user.setPassword(userBody.getPassword());
		userRepository.save(user);
		
		return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Iterable<UserEntity>> getAllUsers() {
		Iterable<UserEntity> users = userRepository.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserEntity> getUserById(String id) {
		try {
			int userId = Integer.parseInt(id);
			UserEntity userEntity = userRepository.findById(userId)
					.orElseThrow(() -> new NotFoundException("User", id));
			return ResponseEntity.ok(userEntity);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
	}

	@Override
	public UserEntity updateUser(String id, UserEntity userBody) {
		UserEntity user = userRepository.findById(Integer.parseInt(id)).get();
		if (user != null) {
			user.setUsername(userBody.getUsername());
			user.setPassword(userBody.getPassword());
			userRepository.save(user);
			return user;
		}
		return new UserEntity();
	}

	@Override
	public UserEntity deleteUser(String id) {
		UserEntity user = userRepository.findById(Integer.parseInt(id)).get();
		if (user != null) {
			userRepository.delete(user);
			return user;
		}
		return new UserEntity();
	}
}
