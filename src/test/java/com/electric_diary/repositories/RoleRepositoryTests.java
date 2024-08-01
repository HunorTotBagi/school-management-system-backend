package com.electric_diary.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.electric_diary.entities.RoleEntity;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RoleRepositoryTests {

	@Mock
	private RoleRepository roleRepository;

	private RoleEntity role;
	private RoleEntity savedRole;

	@BeforeEach
	public void setUp() {
		role = RoleEntity.builder().name("TEACHER").build();

		savedRole = RoleEntity.builder().id(1).name("TEACHER").build();
	}

	@Test
	public void RoleRepository_SaveAll_ReturnSavedRole() {

		// Arrange
		Mockito.when(roleRepository.save(role)).thenReturn(savedRole);

		// Act
		RoleEntity result = roleRepository.save(role);

		// Assert
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getId()).isGreaterThan(0);
	}
}
