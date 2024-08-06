package com.electric_diary.services.impl;

import org.springframework.stereotype.Service;

import com.electric_diary.DTO.Request.GradeRequestDTO;
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
	public GradeEntity assignGrade(GradeRequestDTO gradeRequestDTO) {
		Integer studentId = gradeRequestDTO.getStudentId();
		Integer teacherId = gradeRequestDTO.getTeacherId();
		Integer subjectId = gradeRequestDTO.getSubjectId();
		GradingType gradingType = gradeRequestDTO.getGradingType();
		Integer grade = gradeRequestDTO.getGrade();

		StudentEntity student = getStudentById(studentId);
		TeacherEntity teacher = getTeacherById(teacherId);
		SubjectEntity subject = getSubjectById(subjectId);

		GradeEntity newGrade = new GradeEntity();
		newGrade.setStudent(student);
		newGrade.setTeacher(teacher);
		newGrade.setSubject(subject);
		newGrade.setGrade(grade);
		newGrade.setGradingType(gradingType);
		gradeRepository.save(newGrade);

		sendNewGradeEmailToParent(studentId, grade, student, teacher, subject);

		return newGrade;
	}

	@Override
	public Iterable<GradeEntity> getAllGrades() {
		return gradeRepository.findAll();
	}

	@Override
	public GradeEntity getGradeById(Integer gradeId) {
		return gradeRepository.findById(gradeId).orElseThrow(() -> new NotFoundException("Grade", gradeId));
	}

	@Override
	public GradeEntity updateGrade(Integer gradeId, GradeRequestDTO gradeDTOBody) {
		GradeEntity newGrade = getGradeById(gradeId);

		Integer studentId = gradeDTOBody.getStudentId();
		Integer teacherId = gradeDTOBody.getTeacherId();
		Integer subjectId = gradeDTOBody.getSubjectId();

		StudentEntity student = getStudentById(studentId);
		TeacherEntity teacher = getTeacherById(teacherId);
		SubjectEntity subject = getSubjectById(subjectId);

		newGrade.setStudent(student);
		newGrade.setTeacher(teacher);
		newGrade.setSubject(subject);
		newGrade.setGrade(gradeDTOBody.getGrade());
		newGrade.setGradingType(gradeDTOBody.getGradingType());
		gradeRepository.save(newGrade);

		Integer grade = gradeDTOBody.getGrade();

		sendUpdatedGradeEmailToParent(studentId, grade, student, teacher, subject);

		return newGrade;
	}

	private StudentEntity getStudentById(Integer studentId) {
		return studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Student", studentId));
	}

	private SubjectEntity getSubjectById(Integer subjectId) {
		return subjectRepository.findById(subjectId).orElseThrow(() -> new NotFoundException("Subject", subjectId));
	}

	private TeacherEntity getTeacherById(Integer teacherId) {
		return teacherRepository.findById(teacherId).orElseThrow(() -> new NotFoundException("Teacher", teacherId));
	}

	private void sendNewGradeEmailToParent(Integer studentId, Integer grade, StudentEntity student,
			TeacherEntity teacher, SubjectEntity subject) {
		sendEmailToParent(studentId, grade, student, teacher, subject, "Your child got a new grade");
	}

	private void sendUpdatedGradeEmailToParent(Integer studentId, Integer grade, StudentEntity student,
			TeacherEntity teacher, SubjectEntity subject) {
		sendEmailToParent(studentId, grade, student, teacher, subject, "Your child's grade has been updated");
	}

	private void sendEmailToParent(Integer studentId, Integer grade, StudentEntity student, TeacherEntity teacher,
			SubjectEntity subject, String emailSubject) {
		ParentEntity parent = parentRepositroy.findByStudentsId(studentId);
		if (parent != null && parent.getEmail() != null) {
			EmailEntity emailObject = new EmailEntity();
			emailObject.setTo(parent.getEmail());
			emailObject.setSubject(emailSubject);
			emailObject.setText("Dear " + parent.getFirstName() + ",\n\n" + "Your child " + student.getFirstName()
					+ " has received a grade of " + grade + " in " + subject.getName() + ".\n\n" + "Best regards,\n"
					+ teacher.getFirstName() + " " + teacher.getLastName());
			emailService.sendSimpleMessage(emailObject);
		}
	}
}
