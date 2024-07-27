package com.electric_diary.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
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

	@ManyToMany
	@JoinTable(name = "student_enrolled", joinColumns = @JoinColumn(name = "subject_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	private Set<StudentEntity> enrolledStudents = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "subject", orphanRemoval = true)
	private Set<TeacherEntity> teachers = new HashSet<TeacherEntity>();
	
	public void enrolStudents(StudentEntity student) {
		enrolledStudents.add(student);
	}
}
