package com.electric_diary.entities;

import java.util.ArrayList;
import java.util.List;

import com.electric_diary.security.Views;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

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

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonView(Views.Parent.class)
	private ClassEntity newClass;
	
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GradeEntity> grades = new ArrayList<>();

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
}
