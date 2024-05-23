package com.electric_diary.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electric_diary.entities.SubjectEntity;
import com.electric_diary.repositories.SubjectRepository;
import com.electric_diary.services.SubjectService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class SubjectServiceImpl implements SubjectService {

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected SubjectRepository subjectRepository;

	@Override
	public SubjectEntity createSubject(SubjectEntity subjectBody) {
		SubjectEntity subject = new SubjectEntity();
		subject.setName(subjectBody.getName());
		subject.setWeeklyFund(subjectBody.getWeeklyFund());
		subjectRepository.save(subject);
		return subject;
	}

	@Override
	public Iterable<SubjectEntity> getAllSubjects() {
		return subjectRepository.findAll();
	}

	@Override
	public SubjectEntity getSubjectById(String id) {
		return subjectRepository.findById(Integer.parseInt(id)).get();
	}

	@Override
	public SubjectEntity updateSubject(String id, SubjectEntity subjectBody) {
		SubjectEntity subject = subjectRepository.findById(Integer.parseInt(id)).get();
		if (subject != null) {
			subject.setName(subjectBody.getName());
			subject.setWeeklyFund(subjectBody.getWeeklyFund());
			subjectRepository.save(subject);
			return subject;
		}
		return new SubjectEntity();
	}

	@Override
	public SubjectEntity deleteSubject(String id) {
		SubjectEntity subject = subjectRepository.findById(Integer.parseInt(id)).get();
		if (subject != null) {
			subjectRepository.delete(subject);
			return subject;
		}
		return new SubjectEntity();
	}
}
