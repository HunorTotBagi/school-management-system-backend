package com.electric_diary.entities;

import java.util.HashSet;
import java.util.Set;

import com.electric_diary.security.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class StudentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Student.class)
	private Integer id;

	@JsonView(Views.Student.class)
	private String firstName;

	@JsonView(Views.Parent.class)
	private String lastName;

	@NotNull
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonView(Views.Parent.class)
	private ClassEntity newClass;

	@NotNull
	@JsonIgnore
	@OneToMany(mappedBy = "student")
	private Set<GradeEntity> grades = new HashSet<GradeEntity>();

	@JsonIgnore
	@JsonView(Views.Parent.class)
	@ManyToMany(mappedBy = "enrolledStudents")
	private Set<SubjectEntity> subjects = new HashSet<>();
	
	@JsonView(Views.Parent.class)
	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull(message = "Parent name must be provided.")
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	private ParentEntity parent; 

	public StudentEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ClassEntity getNewClass() {
		return newClass;
	}

	public void setNewClass(ClassEntity newClass) {
		this.newClass = newClass;
	}

	public Set<GradeEntity> getGrades() {
		return grades;
	}

	public void setGrades(Set<GradeEntity> grades) {
		this.grades = grades;
	}

	public Set<SubjectEntity> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(Set<SubjectEntity> subjects) {
		this.subjects = subjects;
	}

	public ParentEntity getParent() {
		return parent;
	}

	public void setParent(ParentEntity parent) {
		this.parent = parent;
	}
}
