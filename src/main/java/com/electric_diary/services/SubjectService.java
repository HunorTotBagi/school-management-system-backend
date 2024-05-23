package com.electric_diary.services;

import com.electric_diary.entities.SubjectEntity;

public interface SubjectService {
	public SubjectEntity createSubject(SubjectEntity subjectBody);

	public Iterable<SubjectEntity> getAllSubjects();

	public SubjectEntity getSubjectById(String id);

	public SubjectEntity updateSubject(String id, SubjectEntity subjectBody);

	public SubjectEntity deleteSubject(String id);
}