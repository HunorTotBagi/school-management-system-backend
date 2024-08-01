package com.electric_diary.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.electric_diary.entities.ClassEntity;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClassRepositoryTests {

	@Mock
	private ClassRepository classRepository;

	private ClassEntity newClass;
	private ClassEntity savedClass;

	@BeforeEach
	public void setUp() {
		newClass = ClassEntity.builder().name("8.A").build();

		savedClass = ClassEntity.builder().id(1).name("8.A").build();
	}

	@Test
	public void ClassRepository_SaveAll_ReturnSavedClass() {

		// Arrange
		Mockito.when(classRepository.save(newClass)).thenReturn(savedClass);

		// Act
		ClassEntity result = classRepository.save(newClass);

		// Assert
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getId()).isGreaterThan(0);
	}
}
