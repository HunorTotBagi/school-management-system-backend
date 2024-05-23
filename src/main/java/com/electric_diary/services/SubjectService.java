package com.electric_diary.services;

import com.electric_diary.entities.SubjectEntity;

public interface SubjectService {
	public SubjectEntity createSubject(String name, String weeklyFund);
	
	public Iterable<SubjectEntity> getAllSubjects();
	
	public SubjectEntity getSubjectById(String id);
	
	public SubjectEntity updateSubject(String id, String name, String weeklyFund);
	
	public SubjectEntity deleteSubject(String id);
}