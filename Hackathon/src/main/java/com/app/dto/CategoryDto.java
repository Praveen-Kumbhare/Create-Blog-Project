package com.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
	private Long categoryId;
	
	@NotBlank
	@Size(min = 4,message = "Min size of category title is 4 ")
	private String title;
	@NotBlank
	@Size(min = 10,message = "Min size of category description is 4 ")
	private String description;
}
