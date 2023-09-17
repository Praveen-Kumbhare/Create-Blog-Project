package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Blogs;
import com.app.entities.Category;
import com.app.entities.User;


public interface  BlogRepository  extends JpaRepository<Blogs, Long> {
	
	List<Blogs> findByUser(User user);
	List<Blogs> findByCategory(Category category);
	
	List<Blogs>findByTitleContaining(String title);
	@Query("select b from Blogs b where b.title like :key")
	List<Blogs> searchByTitle(@Param("key") String title);

}
