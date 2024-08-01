package com.electric_diary.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.electric_diary.entities.TeacherEntity;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TeacherRepositoryTests {
	@Mock
	private TeacherRepository teacherRepository;

	private TeacherEntity teacher;
	private TeacherEntity savedTeacher;

	@BeforeEach
	public void setUp() {
		teacher = TeacherEntity.builder()
				.firstName("Milan")
				.lastName("Čeliković")
				.build();

		savedTeacher = TeacherEntity.builder()
				.id(1)
				.firstName("Milan")
				.lastName("Čeliković")
				.build();
	}

	@Test
	public void TeacherRepository_SaveAll_ReturnSavedTeacher() {
		// Arrange
		Mockito.when(teacherRepository.save(teacher)).thenReturn(savedTeacher);

		// Act
		TeacherEntity result = teacherRepository.save(teacher);

		// Assert
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getId()).isGreaterThan(0);
	}
}
