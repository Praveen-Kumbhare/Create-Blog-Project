package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User extends BaseEntity{

	@Column(name = "username", length = 50)
	private String username;
	@Column(unique = true)
	private String email;
	@Column(length = 50)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@Column(length = 100)
	private String profilePic;
	private LocalDate created_time = LocalDate.now();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Blogs> post = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();



}
