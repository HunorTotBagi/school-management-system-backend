package com.electric_diary.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.electric_diary.entities.ParentEntity;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ParentRepositoryTests {
    @Mock
    private ParentRepository parentRepository;

    @Test
    public void ParentRepository_SaveAll_ReturnSavedParent() {
        // Arrange
    	ParentEntity parent = ParentEntity.builder()
                .firstName("Ana")
                .lastName("Nikolic")
                .email("ana.nikolic@gmail.com")
                .build();

    	ParentEntity savedParent = ParentEntity.builder()
                .id(1)
                .firstName("Ana")
                .lastName("Nikolic")
                .email("ana.nikolic@gmail.com")
                .build();
        
        Mockito.when(parentRepository.save(parent)).thenReturn(savedParent);

        // Act
        ParentEntity result = parentRepository.save(parent);

        // Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isGreaterThan(0);
    }
}
