package com.electric_diary.services;

import com.electric_diary.DTO.GradeDTO;
import com.electric_diary.entities.GradeEntity;

public interface GradeService {
	public GradeEntity assignGrade(GradeDTO gradeDTOBody);

	public Iterable<GradeEntity> getAllGrades();

	public GradeEntity getGradeById(String id);

	public GradeEntity updateGrade(String id, GradeDTO gradeDTOBody);
}
