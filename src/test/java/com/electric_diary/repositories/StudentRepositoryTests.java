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
import com.electric_diary.entities.StudentEntity;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentRepositoryTests {
	@Mock
	private StudentRepository studentRepository;

	private StudentEntity student;
	private StudentEntity savedStudent;

	@BeforeEach
	public void setUp() {
		ClassEntity newClass = new ClassEntity();

		student = StudentEntity.builder()
				.firstName("Nikola")
				.lastName("Vetnić")
				.newClass(newClass)
				.build();

		savedStudent = StudentEntity.builder()
				.id(1)
				.firstName("Nikola")
				.lastName("Vetnić")
				.newClass(newClass)
				.build();
	}

	@Test
	public void RoleRepository_SaveAll_ReturnSavedRole() {
		// Arrange
		Mockito.when(studentRepository.save(student)).thenReturn(savedStudent);

		// Act
		StudentEntity result = studentRepository.save(student);

		// Assert
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getId()).isGreaterThan(0);
	}
}
