package com.electric_diary.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electric_diary.entities.GradeDTO;
import com.electric_diary.entities.GradeEntity;
import com.electric_diary.entities.StudentEntity;
import com.electric_diary.entities.SubjectEntity;
import com.electric_diary.entities.TeacherEntity;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.GradeRepository;
import com.electric_diary.repositories.StudentRepository;
import com.electric_diary.repositories.SubjectRepository;
import com.electric_diary.repositories.TeacherRepository;
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

	@Override
	public GradeEntity assignGrade(GradeDTO gradeDTOBody) {
		String studentId = gradeDTOBody.getStudentId();
		String teacherId = gradeDTOBody.getStudentId();
		String subjectId = gradeDTOBody.getStudentId();

		StudentEntity student = studentRepository.findById(Integer.parseInt(studentId))
				.orElseThrow(() -> new NotFoundException("Student", studentId));
		TeacherEntity teacher = teacherRepository.findById(Integer.parseInt(teacherId))
				.orElseThrow(() -> new NotFoundException("Teacher", teacherId));
		SubjectEntity subject = subjectRepository.findById(Integer.parseInt(subjectId))
				.orElseThrow(() -> new NotFoundException("Subject", subjectId));

		GradeEntity grade = new GradeEntity();
		grade.setStudent(student);
		grade.setTeacher(teacher);
		grade.setSubject(subject);
		grade.setGrade(gradeDTOBody.getGrade());
		grade.setGradingType(gradeDTOBody.getGradingType());
		gradeRepository.save(grade);
		
		return grade;
	}
}
