package com.electric_diary.repositories;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
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

	@Test
	public void ClassRepository_SaveAll_ReturnSavedClass() {
		// Arrange
		ClassEntity newClass = ClassEntity.builder()
				.name("8.A")
				.build();

		ClassEntity savedClass = ClassEntity.builder()
				.id(1)
				.name("8.A")
				.build();
		
		Mockito.when(classRepository.save(newClass)).thenReturn(savedClass);

		// Act
		ClassEntity result = classRepository.save(newClass);

		// Assert
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getId()).isGreaterThan(0);
	}
	
	@Test
	public void ClassRepository_FindAll_ReturnMoreThanOneClass() {
	    // Arrange
	    ClassEntity firstClass = ClassEntity.builder()
	            .name("2.C")
	            .build();
	    
	    ClassEntity secondClass = ClassEntity.builder()
	            .name("5.D")
	            .build();
	    
	    List<ClassEntity> classList = Arrays.asList(firstClass, secondClass);

	    Mockito.when(classRepository.findAll()).thenReturn(classList);
	    
	    // Act
	    Iterable<ClassEntity> result = classRepository.findAll();
	    
	    // Assert
	    Assertions.assertThat(result).isNotNull();
	    Assertions.assertThat(result).hasSize(2);
	    Assertions.assertThat(result).containsExactlyInAnyOrder(firstClass, secondClass);
	}
	
	@Test
	public void ClassRepository_FindById_ReturnClass() {
	    // Arrange
	    ClassEntity newClass = ClassEntity.builder()
	            .id(1)
	            .name("2.C")
	            .build();
	    
	    Mockito.when(classRepository.findById(newClass.getId())).thenReturn(Optional.of(newClass));
	    
	    // Act
	    Optional<ClassEntity> result = classRepository.findById(newClass.getId());
	    
	    // Assert
	    Assertions.assertThat(result).isPresent();
	    Assertions.assertThat(result.get()).isEqualTo(newClass);
	}
}
