package com.electric_diary.services.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.electric_diary.entities.StudentEntity;
import com.electric_diary.entities.SubjectEntity;
import com.electric_diary.exception.CustomBadRequestException;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.StudentRepository;
import com.electric_diary.repositories.SubjectRepository;
import com.electric_diary.services.SubjectService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class SubjectServiceImpl implements SubjectService {
	@PersistenceContext
	protected EntityManager entityManager;

	private final SubjectRepository subjectRepository;
	private final StudentRepository studentRepository;

	public SubjectServiceImpl(final SubjectRepository subjectRepository, final StudentRepository studentRepository) {
		this.subjectRepository = subjectRepository;
		this.studentRepository = studentRepository;
	}

	@Override
	public SubjectEntity createSubject(SubjectEntity subjectBody, BindingResult result) {
		if (result.hasErrors())
			throw new CustomBadRequestException(result);

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
		try {
			int subjectId = Integer.parseInt(id);
			SubjectEntity subject = subjectRepository.findById(subjectId)
					.orElseThrow(() -> new NotFoundException("Subject", id));
			return subject;
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
	}

	@Override
	public SubjectEntity updateSubject(String id, SubjectEntity subjectBody) {
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
			return subject;
		} else {
			throw new NotFoundException("Subject", id);
		}
	}

	@Override
	public SubjectEntity deleteSubject(String id) {
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
			return subject;
		} else {
			throw new NotFoundException("Subject", id);
		}
	}

	@Override
	public SubjectEntity enrollStudentToSubject(String subjectId, String studentId) {
		int subjectInt = Integer.parseInt(subjectId);
		SubjectEntity subject = subjectRepository.findById(subjectInt)
				.orElseThrow(() -> new NotFoundException("Subject", subjectId));

		int studentInt = Integer.parseInt(studentId);
		StudentEntity student = studentRepository.findById(studentInt)
				.orElseThrow(() -> new NotFoundException("Student", studentId));

		subject.enrolStudents(student);
		subjectRepository.save(subject);
		return subject;
	}
}
