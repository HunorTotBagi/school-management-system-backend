package com.electric_diary.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SubjectEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private Integer weeklyFund;

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
}
