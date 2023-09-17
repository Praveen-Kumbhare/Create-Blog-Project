package com.app.custom_exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException {

	private String resourceName; 
	private String fieldName;
	private Long fieldValue;
	
	
	public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}


	public ResourceNotFoundException(String resourceName) {
		super();
		this.resourceName = resourceName;
	}
	
	
	

}
