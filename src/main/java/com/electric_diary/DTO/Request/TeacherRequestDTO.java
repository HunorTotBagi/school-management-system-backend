package com.electric_diary.DTO.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeacherRequestDTO {
	private String firstName;
	private String lastName;
	private Integer subjectId;
	private Integer classId;
	private Integer userId;
}
