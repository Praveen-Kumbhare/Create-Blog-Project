package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
	private long userId;

	@NotEmpty
	@Size(min = 4, message = "Username must be of min 4 character")
	private String username;

	@Email(message = "Email address is not valid !!")
	private String email;

	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars")
	private String password;
	private String profilePic;
}
