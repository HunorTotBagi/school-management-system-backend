package com.electric_diary.DTO.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentRequestDTO {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Integer classId;
	private Integer parentId;
}
