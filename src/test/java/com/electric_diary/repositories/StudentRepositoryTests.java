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

import com.electric_diary.entities.ClassEntity;
import com.electric_diary.entities.StudentEntity;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentRepositoryTests {
	@Mock
	private StudentRepository studentRepository;

	@Test
	public void StudentRepository_SaveAll_ReturnSavedStudent() {
		// Arrange
		ClassEntity newClass = new ClassEntity();

		StudentEntity student = StudentEntity.builder()
				.firstName("Nikola")
				.lastName("Vetnić")
				.newClass(newClass)
				.build();

		StudentEntity savedStudent = StudentEntity.builder()
				.id(1)
				.firstName("Nikola")
				.lastName("Vetnić")
				.newClass(newClass)
				.build();
		
		Mockito.when(studentRepository.save(student)).thenReturn(savedStudent);

		// Act
		StudentEntity result = studentRepository.save(student);

		// Assert
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getId()).isGreaterThan(0);
	}
	
	@Test
	public void StudentRepository_FindAll_ReturnMoreThanOneStudent() {
	    // Arrange
		ClassEntity firstNewClass = new ClassEntity();
		ClassEntity secondNewClass = new ClassEntity();

		StudentEntity firstStudent = StudentEntity.builder()
				.firstName("Nikola")
				.lastName("Vetnić")
				.newClass(firstNewClass)
				.build();
		
		StudentEntity secondStudent = StudentEntity.builder()
				.firstName("Nikola")
				.lastName("Dmitrašinović")
				.newClass(secondNewClass)
				.build();
	    
	    List<StudentEntity> studentList = Arrays.asList(firstStudent, secondStudent);

	    Mockito.when(studentRepository.findAll()).thenReturn(studentList);
	    
	    // Act
	    Iterable<StudentEntity> result = studentRepository.findAll();
	    
	    // Assert
	    Assertions.assertThat(result).isNotNull();
	    Assertions.assertThat(result).hasSize(2);
	    Assertions.assertThat(result).containsExactlyInAnyOrder(firstStudent, secondStudent);
	}
}
