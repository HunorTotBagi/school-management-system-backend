package com.electric_diary.handlers;

import com.electric_diary.entities.UserEntity;

public interface UserService {
	public UserEntity createUser(String username, String password);

	public Iterable<UserEntity> getAllUsers();

	public UserEntity getUserById(String id);

	public UserEntity updateUser(String id, String username, String password);

	public UserEntity deleteUser(String id);
}
