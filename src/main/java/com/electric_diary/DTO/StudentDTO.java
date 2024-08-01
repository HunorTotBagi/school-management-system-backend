package com.electric_diary.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {
	private String firstName;
	private String lastName;
	private String classId;
	private String parentId;
}
