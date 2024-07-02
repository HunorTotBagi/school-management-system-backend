package com.electric_diary.entities;

import com.electric_diary.enums.GradingType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class GradeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull(message = "Grade name must be provided.")
	@Min(1)
	@Max(5)
	private Integer grade;

	@NotNull
    @Enumerated(EnumType.STRING)
	private GradingType gradingType;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private StudentEntity student;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private TeacherEntity teacher;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private SubjectEntity subject;

	public GradeEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public GradingType getGradingType() {
		return gradingType;
	}

	public void setGradingType(GradingType gradingType) {
		this.gradingType = gradingType;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public TeacherEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}
}
