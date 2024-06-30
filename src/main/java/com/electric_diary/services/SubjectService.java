package com.electric_diary.services;

import org.springframework.validation.BindingResult;

import com.electric_diary.entities.SubjectEntity;

public interface SubjectService {
	public SubjectEntity createSubject(SubjectEntity subjectBody, BindingResult result);

	public Iterable<SubjectEntity> getAllSubjects();

	public SubjectEntity getSubjectById(String id);

	public SubjectEntity updateSubject(String id, SubjectEntity subjectBody);

	public SubjectEntity deleteSubject(String id);
	
	public SubjectEntity enrollStudentToSubject(String subjectId, String studentId);
}