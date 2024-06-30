package com.electric_diary.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class SubjectEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private Integer weeklyFund;

	@NotNull
	@JsonIgnore
	@OneToMany(mappedBy = "subject")
	private Set<GradeEntity> grades = new HashSet<GradeEntity>();

	public SubjectEntity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWeeklyFund() {
		return this.weeklyFund;
	}

	public void setWeeklyFund(Integer weeklyFund) {
		this.weeklyFund = weeklyFund;
	}

	public Set<GradeEntity> getGrades() {
		return grades;
	}

	public void setGrades(Set<GradeEntity> grades) {
		this.grades = grades;
	}
}
