package com.electric_diary.services;

import com.electric_diary.DTO.UserDTO;
import com.electric_diary.entities.UserEntity;

public interface UserService {
	public UserEntity createUser(UserDTO userDTOBody);

	public Iterable<UserEntity> getAllUsers();

	public UserEntity getUserById(Integer userId);

	public UserEntity updateUser(Integer userId, UserDTO userDTOBody);

	public UserEntity deleteUser(Integer userId);
}
