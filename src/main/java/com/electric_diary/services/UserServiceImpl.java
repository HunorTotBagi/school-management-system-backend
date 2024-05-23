package com.electric_diary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electric_diary.entities.UserEntity;
import com.electric_diary.repositories.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class UserServiceImpl implements UserService{

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected UserRepository userRepository;

	@Override
	public UserEntity createUser(String username, String password) {
		UserEntity user = new UserEntity();
		user.setUsername(username);
		user.setPassword(password);
		userRepository.save(user);
		return user;
	}

	@Override
	public Iterable<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity getUserById(String id) {
		return userRepository.findById(Integer.parseInt(id)).get();
	}

	@Override
	public UserEntity updateUser(String id, String username, String password) {
		UserEntity user = userRepository.findById(Integer.parseInt(id)).get();
		if (user != null) {
			user.setUsername(username);
			user.setPassword(password);
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
