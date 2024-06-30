package com.electric_diary.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electric_diary.entities.EmailObject;
import com.electric_diary.entities.GradeDTO;
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

@Service
public class GradeServiceImpl implements GradeService {
	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private ParentRepository parentRepositroy;

	@Autowired
	private EmailService emailService;

	@Override
	public GradeEntity assignGrade(GradeDTO gradeDTOBody) {
		String studentId = gradeDTOBody.getStudentId();
		String teacherId = gradeDTOBody.getTeacherId();
		String subjectId = gradeDTOBody.getSubjectId();
		GradingType gradingType = gradeDTOBody.getGradingType();

		Integer grade = gradeDTOBody.getGrade();
		if (grade < 1 || grade > 6)
			throw new NotFoundException("Student", studentId);

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
		newGrade.setGrade(gradeDTOBody.getGrade());

		newGrade.setGradingType(gradingType);
		gradeRepository.save(newGrade);

		ParentEntity parent = parentRepositroy.findByStudentsId(Integer.parseInt(studentId));
		if (parent != null && parent.getEmail() != null) {
			EmailObject emailObject = new EmailObject();
			emailObject.setTo(parent.getEmail());
			emailObject.setSubject("Your child's grade has been updated");
			emailObject.setText("Dear " + parent.getFirstName() + ",\n\n" + "Your child " + student.getFirstName()
					+ " has received a grade of " + grade + " in " + subject.getName() + ".\n\n" + "Best regards,\n"
					+ teacher.getFirstName() + " " + teacher.getLastName()); 
			emailService.sendSimpleMessage(emailObject);
		}
		return newGrade;
	}

	@Override
	public Iterable<GradeEntity> getAllGrades() {
		Iterable<GradeEntity> grades = gradeRepository.findAll();
		return grades;
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
			GradeEntity grade = optionalGrade.get();
			String studentId = gradeDTOBody.getStudentId();
			String teacherId = gradeDTOBody.getTeacherId();
			String subjectId = gradeDTOBody.getSubjectId();

			StudentEntity student = studentRepository.findById(Integer.parseInt(studentId))
					.orElseThrow(() -> new NotFoundException("Student", studentId));
			TeacherEntity teacher = teacherRepository.findById(Integer.parseInt(teacherId))
					.orElseThrow(() -> new NotFoundException("Teacher", teacherId));
			SubjectEntity subject = subjectRepository.findById(Integer.parseInt(subjectId))
					.orElseThrow(() -> new NotFoundException("Subject", subjectId));

			grade.setStudent(student);
			grade.setTeacher(teacher);
			grade.setSubject(subject);
			grade.setGrade(gradeDTOBody.getGrade());
			grade.setGradingType(gradeDTOBody.getGradingType());
			gradeRepository.save(grade);

			return grade;
		} else {
			throw new NotFoundException("Grade", id);
		}
	}
}
