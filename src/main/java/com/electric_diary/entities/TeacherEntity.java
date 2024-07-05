package com.electric_diary.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TeacherEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull(message = "First name must be provided.")
	@Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters long.")
	private String firstName;

	@NotNull(message = "Last name must be provided.")
	@Size(min = 2, max = 30, message = "Last name must be between {min} and {max} characters long.")
	private String lastName;

	@JsonIgnore
	@OneToMany(mappedBy = "teacher")
	private Set<GradeEntity> grades = new HashSet<GradeEntity>();

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "Subject must be provided.")
	private SubjectEntity subject;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "Class must be provided.")
	private ClassEntity newClass;

	public TeacherEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<GradeEntity> getGrades() {
		return grades;
	}

	public void setGrades(Set<GradeEntity> grades) {
		this.grades = grades;
	}

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}

	public ClassEntity getNewClass() {
		return newClass;
	}

	public void setNewClass(ClassEntity newClass) {
		this.newClass = newClass;
	}
}
