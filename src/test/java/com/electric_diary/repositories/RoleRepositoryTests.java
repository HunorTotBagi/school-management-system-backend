package com.electric_diary.repositories;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
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

	@Test
	public void RoleRepository_SaveAll_ReturnSavedRole() {
		// Arrange
		RoleEntity role = RoleEntity.builder()
				.name("TEACHER")
				.build();

		RoleEntity savedRole = RoleEntity.builder()
				.id(1)
				.name("TEACHER")
				.build();
		
		Mockito.when(roleRepository.save(role)).thenReturn(savedRole);

		// Act
		RoleEntity result = roleRepository.save(role);

		// Assert
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getId()).isGreaterThan(0);
	}
	
	@Test
	public void RoleRepository_FindAll_ReturnMoreThanOneRole() {
	    // Arrange
		RoleEntity firstRole = RoleEntity.builder()
				.name("TEACHER")
				.build();
	    
		RoleEntity secondRole = RoleEntity.builder()
				.name("ADMIN")
				.build();
	    
	    List<RoleEntity> roleList = Arrays.asList(firstRole, secondRole);

	    Mockito.when(roleRepository.findAll()).thenReturn(roleList);
	    
	    // Act
	    Iterable<RoleEntity> result = roleRepository.findAll();
	    
	    // Assert
	    Assertions.assertThat(result).isNotNull();
	    Assertions.assertThat(result).hasSize(2);
	    Assertions.assertThat(result).containsExactlyInAnyOrder(firstRole, secondRole);
	}
}
