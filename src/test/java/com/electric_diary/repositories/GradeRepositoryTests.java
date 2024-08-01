package com.electric_diary.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.electric_diary.entities.GradeEntity;
import com.electric_diary.enums.GradingType;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GradeRepositoryTests {
	@Mock
	private GradeRepository gradeRepository;

	private GradeEntity grade;
	private GradeEntity savedGrade;

	@BeforeEach
	public void setUp() {
		
		grade = GradeEntity.builder()
				.grade(5)
				.gradingType(GradingType.HOMEWORK)
				.build();

		savedGrade = GradeEntity.builder()
				.id(1)
				.grade(5)
				.gradingType(GradingType.HOMEWORK)
				.build();
	}

	@Test
	public void GradeRepository_SaveAll_ReturnSavedGrade() {
		// Arrange
		Mockito.when(gradeRepository.save(grade)).thenReturn(savedGrade);

		// Act
		GradeEntity result = gradeRepository.save(grade);

		// Assert
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getId()).isGreaterThan(0);
	}
}
