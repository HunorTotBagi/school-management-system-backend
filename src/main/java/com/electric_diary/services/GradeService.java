package com.electric_diary.services;

import com.electric_diary.entities.GradeDTO;
import com.electric_diary.entities.GradeEntity;

public interface GradeService {
	public GradeEntity assignGrade(GradeDTO gradeDTOBody);
}
