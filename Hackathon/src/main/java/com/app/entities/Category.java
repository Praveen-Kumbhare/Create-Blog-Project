package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "categories_tbl")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Category extends BaseEntity {

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
	private List<Blogs> post = new ArrayList<>();

}
