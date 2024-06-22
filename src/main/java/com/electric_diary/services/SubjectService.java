package com.electric_diary.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.SubjectEntity;

public interface SubjectService {
	public ResponseEntity<SubjectEntity> createSubject(SubjectEntity subjectBody, BindingResult result);

	public ResponseEntity<Iterable<SubjectEntity>> getAllSubjects();

	public ResponseEntity<SubjectEntity> getSubjectById(String id);

	public ResponseEntity<SubjectEntity> updateSubject(String id, SubjectEntity subjectBody);

	public ResponseEntity<SubjectEntity> deleteSubject(String id);
}