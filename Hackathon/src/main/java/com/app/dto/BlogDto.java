package com.app.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BlogDto {

	private Long id;

	private String title;

	private String content;

	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
}
