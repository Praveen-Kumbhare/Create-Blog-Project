package com.app.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "blogs_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Blogs extends BaseEntity {

	@Column(name = "post_title" , length = 100,nullable = false)
	private String title;
	@Column(name = "content",length = 10000)
	private String content;
	@Column(name = "created_time")
	private Date addedDate;
	@Column(name = "image")
	private String imageName;
	

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;

}
