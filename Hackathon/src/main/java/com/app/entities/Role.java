package com.app.entities;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Role extends BaseEntity {
	private String name;

}
