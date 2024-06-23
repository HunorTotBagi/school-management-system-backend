package com.electric_diary.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.UserEntity;

public interface UserService {
	public ResponseEntity<UserEntity> createUser(UserEntity userBody, BindingResult result);

	public ResponseEntity<Iterable<UserEntity>> getAllUsers();

	public ResponseEntity<UserEntity> getUserById(String id);

	public ResponseEntity<UserEntity> updateUser(String id, UserEntity userBody);

	public ResponseEntity<UserEntity> deleteUser(String id);
}
