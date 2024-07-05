package com.electric_diary.entities;

import java.util.ArrayList;
import java.util.List;

import com.electric_diary.security.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ClassEntity {
	@Id
	@JsonView(Views.Parent.class)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@JsonView(Views.Parent.class)
	private String name;

	@JsonIgnore
	@JsonView(Views.Parent.class)
	@OneToMany(mappedBy = "newClass", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<StudentEntity> students = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "newClass", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<TeacherEntity> teachers = new ArrayList<>();

	public ClassEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(List<StudentEntity> students) {
		this.students = students;
	}
}
