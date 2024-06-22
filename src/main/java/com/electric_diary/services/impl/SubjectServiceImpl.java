package com.electric_diary.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.SubjectEntity;
import com.electric_diary.exception.CustomBadRequestException;
import com.electric_diary.exception.NotFoundException;
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
	public ResponseEntity<SubjectEntity> createSubject(SubjectEntity subjectBody, BindingResult result) {
		if (result.hasErrors())
			throw new CustomBadRequestException(result);

		SubjectEntity subject = new SubjectEntity();
		subject.setName(subjectBody.getName());
		subject.setWeeklyFund(subjectBody.getWeeklyFund());
		subjectRepository.save(subject);

		return new ResponseEntity<>(subject, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Iterable<SubjectEntity>> getAllSubjects() {
		Iterable<SubjectEntity> subjects = subjectRepository.findAll();
		return new ResponseEntity<>(subjects, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SubjectEntity> getSubjectById(String id) {
		try {
			int subjectId = Integer.parseInt(id);
			SubjectEntity subjectEntity = subjectRepository.findById(subjectId)
					.orElseThrow(() -> new NotFoundException("Subject", id));
			return ResponseEntity.ok(subjectEntity);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
	}

	@Override
	public ResponseEntity<SubjectEntity> updateSubject(String id, SubjectEntity subjectBody) {
		int subjectId;
		try {
			subjectId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		Optional<SubjectEntity> optionalSubject = subjectRepository.findById(subjectId);
		if (optionalSubject.isPresent()) {
			SubjectEntity subject = optionalSubject.get();
			subject.setName(subjectBody.getName());
			subject.setWeeklyFund(subjectBody.getWeeklyFund());
			subjectRepository.save(subject);
			return new ResponseEntity<>(subject, HttpStatus.OK);
		} else {
			throw new NotFoundException("Subject", id);
		}
	}

	@Override
	public ResponseEntity<SubjectEntity> deleteSubject(String id) {
		int subjectId;
		try {
			subjectId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}

		Optional<SubjectEntity> optionalSubject = subjectRepository.findById(subjectId);
		if (optionalSubject.isPresent()) {
			SubjectEntity subject = optionalSubject.get();
			subjectRepository.delete(subject);
			return new ResponseEntity<>(subject, HttpStatus.OK);
		} else {
			throw new NotFoundException("Subject", id);
		}
	}
}
