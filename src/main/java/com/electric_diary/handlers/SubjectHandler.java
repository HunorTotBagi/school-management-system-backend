package com.electric_diary.handlers;

import com.electric_diary.entities.SubjectEntity;

public interface SubjectHandler {
	public SubjectEntity createSubject(String name, String weeklyFund);
	
	public Iterable<SubjectEntity> getAllSubjects();
	
	public SubjectEntity getSubjectById(String id);
	
	public SubjectEntity updateSubject(String id, String name, String weeklyFund);
	
	public SubjectEntity deleteSubject(String id);
}