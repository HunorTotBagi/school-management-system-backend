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

import com.electric_diary.entities.GradeEntity;
import com.electric_diary.enums.GradingType;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GradeRepositoryTests {
	@Mock
	private GradeRepository gradeRepository;

	@Test
	public void GradeRepository_SaveAll_ReturnSavedGrade() {
		// Arrange
		GradeEntity grade = GradeEntity.builder()
				.grade(5)
				.gradingType(GradingType.HOMEWORK)
				.build();

		GradeEntity savedGrade = GradeEntity.builder()
				.id(1)
				.grade(5)
				.gradingType(GradingType.HOMEWORK)
				.build();
		
		Mockito.when(gradeRepository.save(grade)).thenReturn(savedGrade);

		// Act
		GradeEntity result = gradeRepository.save(grade);

		// Assert
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getId()).isGreaterThan(0);
	}
	
	@Test
	public void GradeRepository_FindAll_ReturnMoreThanOneGrade() {
	    // Arrange
		GradeEntity firstGrade = GradeEntity.builder()
				.grade(5)
				.gradingType(GradingType.HOMEWORK)
				.build();
	    
		GradeEntity secondGrade = GradeEntity.builder()
				.grade(3)
				.gradingType(GradingType.WRITTEN_EXAMN)
				.build();
	    
	    List<GradeEntity> gradeList = Arrays.asList(firstGrade, secondGrade);

	    Mockito.when(gradeRepository.findAll()).thenReturn(gradeList);
	    
	    // Act
	    Iterable<GradeEntity> result = gradeRepository.findAll();
	    
	    // Assert
	    Assertions.assertThat(result).isNotNull();
	    Assertions.assertThat(result).hasSize(2);
	    Assertions.assertThat(result).containsExactlyInAnyOrder(firstGrade, secondGrade);
	}
}
