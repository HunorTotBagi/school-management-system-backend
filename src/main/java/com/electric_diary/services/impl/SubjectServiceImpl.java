package com.electric_diary.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.SubjectEntity;
import com.electric_diary.repositories.SubjectRepository;
import com.electric_diary.services.SubjectService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class SubjectServiceImpl extends ErrorMessagesServiceImpl implements SubjectService {

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected SubjectRepository subjectRepository;

	@Override
	public ResponseEntity<?> createSubject(SubjectEntity subjectBody, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		SubjectEntity subject = new SubjectEntity();
		subject.setName(subjectBody.getName());
		subject.setWeeklyFund(subjectBody.getWeeklyFund());
		subjectRepository.save(subject);

		return new ResponseEntity<>(subject, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllSubjects() {
		Iterable<SubjectEntity> subject = subjectRepository.findAll();
		return new ResponseEntity<>(subject, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getSubjectById(String id) {
		try {
			int subjectId = Integer.parseInt(id);
			Optional<SubjectEntity> subjectOptional = subjectRepository.findById(subjectId);

			if (subjectOptional.isPresent()) {
				return new ResponseEntity<>(subjectOptional.get(), HttpStatus.OK);
			} else {
				return createNotFoundResponse("Subject", subjectId);
			}
		} catch (NumberFormatException e) {
			return createBadRequestResponse("Invalid ID format");
		} catch (Exception e) {
			return createErrorResponse(e);
		}
	}

	@Override
	public ResponseEntity<?> updateSubject(String id, SubjectEntity subjectBody) {
		try {
			int subjectId = Integer.parseInt(id);
			Optional<SubjectEntity> optionalSubject = subjectRepository.findById(subjectId);

			if (optionalSubject.isPresent()) {
				SubjectEntity subject = optionalSubject.get();
				subject.setName(subjectBody.getName());
				subject.setWeeklyFund(subjectBody.getWeeklyFund());
				subjectRepository.save(subject);
				return new ResponseEntity<>(subject, HttpStatus.OK);
			} else {
				return createNotFoundResponse("Subject", subjectId);
			}
		} catch (NumberFormatException e) {
			return createBadRequestResponse("Invalid ID format");
		} catch (Exception e) {
			return createErrorResponse(e);
		}
	}

	@Override
	public ResponseEntity<?> deleteSubject(String id) {
		try {
			int subjectId = Integer.parseInt(id);
			Optional<SubjectEntity> optionalSubject = subjectRepository.findById(subjectId);

			if (optionalSubject.isPresent()) {
				SubjectEntity subject = optionalSubject.get();
				subjectRepository.delete(subject);
				return new ResponseEntity<>(subject, HttpStatus.OK);
			} else {
				return createNotFoundResponse("Subject", subjectId);
			}
		} catch (NumberFormatException e) {
			return createBadRequestResponse("Invalid ID format");
		} catch (Exception e) {
			return createErrorResponse(e);
		}
	}
}
