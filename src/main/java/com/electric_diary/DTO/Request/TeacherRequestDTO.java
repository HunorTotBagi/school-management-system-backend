package com.electric_diary.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeacherDTO {
	private String firstName;
	private String lastName;
	private String teacherId;
	private String subjectId;
	private String classId;
	private String userId;
}
