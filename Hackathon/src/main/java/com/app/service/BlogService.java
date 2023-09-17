package com.app.service;

import java.util.List;

import com.app.dto.BlogDto;
import com.app.payloads.BlogResponse;
import com.app.payloads.BlogUserCatResponse;

public interface BlogService {
	
	BlogDto createPost(BlogDto blogDto,Long userId,Long categoryId);
	
	BlogDto updateBlog(BlogDto blogDto,Long blogId);
	
	void deleteBlog(Long blogId);
	
	BlogResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy);
	
	BlogDto getBlogById(Long blogId);
	
	BlogUserCatResponse getBlogsByCategory(String catTitle);
	
	BlogUserCatResponse getBlogsByUser(String user);
	
	List<BlogDto> searchBlog(String keyword);
	
	
}
