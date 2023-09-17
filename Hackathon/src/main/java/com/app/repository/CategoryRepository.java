package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Category;
import com.app.entities.User;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	 Category findByTitle(String title);
}
