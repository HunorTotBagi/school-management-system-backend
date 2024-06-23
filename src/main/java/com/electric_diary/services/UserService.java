package com.electric_diary.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.UserEntity;

public interface UserService {
	public ResponseEntity<UserEntity> createUser(UserEntity userBody, BindingResult result);

	public Iterable<UserEntity> getAllUsers();

	public UserEntity getUserById(String id);

	public UserEntity updateUser(String id, UserEntity userBody);

	public UserEntity deleteUser(String id);
}
