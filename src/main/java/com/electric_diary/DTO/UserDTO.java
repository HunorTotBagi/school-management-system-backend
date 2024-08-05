package com.electric_diary.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
	private String name;
	private String lastName;
	private String password;
	private String email;
	private Integer roleId;
}
