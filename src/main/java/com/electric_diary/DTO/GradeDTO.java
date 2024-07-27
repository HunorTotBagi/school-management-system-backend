package com.electric_diary.DTO;

import com.electric_diary.enums.GradingType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class GradeDTO {
	@Min(value = 1, message = "Grade can't be less than {value}.")
	@Max(value = 5, message = "Grade can't be greater than {value}.")
	private Integer grade;
	private String studentId;
	private String teacherId;
	private String subjectId;
	private GradingType gradingType;

	public GradeDTO() {
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public GradingType getGradingType() {
		return gradingType;
	}

	public void setGradingType(GradingType gradingType) {
		this.gradingType = gradingType;
	}
}
