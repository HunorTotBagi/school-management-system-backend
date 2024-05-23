package com.electric_diary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electric_diary.entities.SubjectEntity;
import com.electric_diary.repositories.SubjectRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class SubjectServiceImpl implements SubjectService{

	@PersistenceContext
	protected EntityManager em;
	
	@Autowired
	protected SubjectRepository subjectRepository;
	
	@Override
	public SubjectEntity createSubject(String name, String weeklyFund) {
		SubjectEntity subject = new SubjectEntity();
		subject.setName(name);
		subject.setWeeklyFund(Integer.parseInt(weeklyFund));
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
	public SubjectEntity updateSubject(String id, String name, String weeklyFund) {
		SubjectEntity subject = subjectRepository.findById(Integer.parseInt(id)).get();
		if (subject != null) {
			subject.setName(name);
			subject.setWeeklyFund(Integer.parseInt(weeklyFund));
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
