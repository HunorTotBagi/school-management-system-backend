package com.electric_diary.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.electric_diary.entities.UserEntity;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTests {
	@Mock
	private UserRepository userRepository;

	@Test
	public void UserRepository_SaveAll_ReturnSavedUser() {
		// Arrange
		UserEntity user = UserEntity.builder()
				.name("Clark")
				.lastName("Kent")
				.password("ver7strong")
				.email("clark.kent@gmail.com")
				.build();

		UserEntity savedUser = UserEntity.builder()
				.id(1)
				.name("Clark")
				.lastName("Kent")
				.password("ver7strong")
				.email("clark.kent@gmail.com")
				.build();
		
		Mockito.when(userRepository.save(user)).thenReturn(savedUser);

		// Act
		UserEntity result = userRepository.save(user);

		// Assert
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getId()).isGreaterThan(0);
	}
}
