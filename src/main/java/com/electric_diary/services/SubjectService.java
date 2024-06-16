package com.electric_diary.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.SubjectEntity;

public interface SubjectService {
	public ResponseEntity<?> createSubject(SubjectEntity subjectBody, BindingResult result);

	public ResponseEntity<?> getAllSubjects();

	public ResponseEntity<?> getSubjectById(String id);

	public ResponseEntity<?> updateSubject(String id, SubjectEntity subjectBody);

	public ResponseEntity<?> deleteSubject(String id);
}