package com.electric_diary.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.electric_diary.entities.SubjectEntity;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SubjectRepositoryTests {
	@Mock
	private SubjectRepository subjectRepository;

	private SubjectEntity subject;
	private SubjectEntity savedSubject;

	@BeforeEach
	public void setUp() {
		subject = SubjectEntity.builder()
				.name("Mathetmatics")
				.weeklyFund(50)
				.build();

		savedSubject = SubjectEntity.builder()
				.id(1)
				.name("Mathetmatics")
				.weeklyFund(50)
				.build();
	}

	@Test
	public void SubjectRepository_SaveAll_ReturnSavedSubject() {
		// Arrange
		Mockito.when(subjectRepository.save(subject)).thenReturn(savedSubject);

		// Act
		SubjectEntity result = subjectRepository.save(subject);

		// Assert
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getId()).isGreaterThan(0);
	}
}
