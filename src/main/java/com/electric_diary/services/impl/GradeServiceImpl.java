package com.electric_diary.services.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.electric_diary.DTO.GradeDTO;
import com.electric_diary.entities.EmailEntity;
import com.electric_diary.entities.GradeEntity;
import com.electric_diary.entities.ParentEntity;
import com.electric_diary.entities.StudentEntity;
import com.electric_diary.entities.SubjectEntity;
import com.electric_diary.entities.TeacherEntity;
import com.electric_diary.enums.GradingType;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.GradeRepository;
import com.electric_diary.repositories.ParentRepository;
import com.electric_diary.repositories.StudentRepository;
import com.electric_diary.repositories.SubjectRepository;
import com.electric_diary.repositories.TeacherRepository;
import com.electric_diary.services.EmailService;
import com.electric_diary.services.GradeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class GradeServiceImpl implements GradeService {
	@PersistenceContext
	protected EntityManager entityManager;

	private final GradeRepository gradeRepository;
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;
	private final SubjectRepository subjectRepository;
	private final ParentRepository parentRepositroy;
	private final EmailService emailService;

	public GradeServiceImpl(final GradeRepository gradeRepository, final StudentRepository studentRepository,
			final TeacherRepository teacherRepository, final SubjectRepository subjectRepository,
			final ParentRepository parentRepositroy, final EmailService emailService) {
		this.gradeRepository = gradeRepository;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
		this.subjectRepository = subjectRepository;
		this.parentRepositroy = parentRepositroy;
		this.emailService = emailService;
	}

	@Override
	public GradeEntity assignGrade(GradeDTO gradeDTOBody) {
		String studentId = gradeDTOBody.getStudentId();
		String teacherId = gradeDTOBody.getTeacherId();
		String subjectId = gradeDTOBody.getSubjectId();
		GradingType gradingType = gradeDTOBody.getGradingType();
		Integer grade = gradeDTOBody.getGrade();

		StudentEntity student = studentRepository.findById(Integer.parseInt(studentId))
				.orElseThrow(() -> new NotFoundException("Student", studentId));
		TeacherEntity teacher = teacherRepository.findById(Integer.parseInt(teacherId))
				.orElseThrow(() -> new NotFoundException("Teacher", teacherId));
		SubjectEntity subject = subjectRepository.findById(Integer.parseInt(subjectId))
				.orElseThrow(() -> new NotFoundException("Subject", subjectId));

		GradeEntity newGrade = new GradeEntity();
		newGrade.setStudent(student);
		newGrade.setTeacher(teacher);
		newGrade.setSubject(subject);
		newGrade.setGrade(grade);
		newGrade.setGradingType(gradingType);
		gradeRepository.save(newGrade);

		ParentEntity parent = parentRepositroy.findByStudentsId(Integer.parseInt(studentId));
		if (parent != null && parent.getEmail() != null) {
			EmailEntity emailObject = new EmailEntity();
			emailObject.setTo(parent.getEmail());
			emailObject.setSubject("Your child got a new grade");
			emailObject.setText("Dear " + parent.getFirstName() + ",\n\n" + "Your child " + student.getFirstName()
					+ " has received a grade of " + grade + " in " + subject.getName() + ".\n\n" + "Best regards,\n"
					+ teacher.getFirstName() + " " + teacher.getLastName());
			emailService.sendSimpleMessage(emailObject);
		}
		
		return newGrade;
	}

	@Override
	public Iterable<GradeEntity> getAllGrades() {
		return gradeRepository.findAll();
	}

	@Override
	public GradeEntity getGradeById(String id) {
		try {
			int gradeId = Integer.parseInt(id);
			GradeEntity grade = gradeRepository.findById(gradeId).orElseThrow(() -> new NotFoundException("Grade", id));
			return grade;
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
	}

	@Override
	public GradeEntity updateGrade(String id, GradeDTO gradeDTOBody) {
		int gradeId;
		try {
			gradeId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Invalid grade ID: " + id);
		}

		Optional<GradeEntity> optionalGrade = gradeRepository.findById(gradeId);
		if (optionalGrade.isPresent()) {
			GradeEntity newGrade = optionalGrade.get();
			String studentId = gradeDTOBody.getStudentId();
			String teacherId = gradeDTOBody.getTeacherId();
			String subjectId = gradeDTOBody.getSubjectId();

			StudentEntity student = studentRepository.findById(Integer.parseInt(studentId))
					.orElseThrow(() -> new NotFoundException("Student", studentId));
			TeacherEntity teacher = teacherRepository.findById(Integer.parseInt(teacherId))
					.orElseThrow(() -> new NotFoundException("Teacher", teacherId));
			SubjectEntity subject = subjectRepository.findById(Integer.parseInt(subjectId))
					.orElseThrow(() -> new NotFoundException("Subject", subjectId));

			newGrade.setStudent(student);
			newGrade.setTeacher(teacher);
			newGrade.setSubject(subject);
			newGrade.setGrade(gradeDTOBody.getGrade());
			newGrade.setGradingType(gradeDTOBody.getGradingType());
			gradeRepository.save(newGrade);
			
			Integer grade = gradeDTOBody.getGrade();
			
			ParentEntity parent = parentRepositroy.findByStudentsId(Integer.parseInt(studentId));
			if (parent != null && parent.getEmail() != null) {
				EmailEntity emailObject = new EmailEntity();
				emailObject.setTo(parent.getEmail());
				emailObject.setSubject("Your child's grade has been updated");
				emailObject.setText("Dear " + parent.getFirstName() + ",\n\n" + "Your child " + student.getFirstName()
						+ " has received a grade of " + grade + " in " + subject.getName() + ".\n\n" + "Best regards,\n"
						+ teacher.getFirstName() + " " + teacher.getLastName());
				emailService.sendSimpleMessage(emailObject);
			}

			return newGrade;
		} else {
			throw new NotFoundException("Grade", id);
		}
	}
}
