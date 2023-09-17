package com.app.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.BlogDto;
import com.app.entities.Blogs;
import com.app.entities.Category;
import com.app.entities.User;
import com.app.payloads.BlogResponse;
import com.app.payloads.BlogUserCatResponse;
import com.app.repository.BlogRepository;
import com.app.repository.CategoryRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogRepository blogRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CategoryRepository catRepo;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public BlogDto createPost(BlogDto blogDto, Long userId, Long categoryId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User id ", userId));
		Category category = catRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id ", categoryId));
		Blogs blog = this.modelmapper.map(blogDto, Blogs.class);
		blog.setImageName("default.png");
		blog.setUser(user);
		blog.setCategory(category);
		blog.setAddedDate(new Date());
		Blogs blogAdded = this.blogRepo.save(blog);
		return this.modelmapper.map(blogAdded, BlogDto.class);
	}

	@Override
	public BlogDto updateBlog(BlogDto blogDto, Long blogId) {
		Blogs blog = this.blogRepo.findById(blogId)
				.orElseThrow(() -> new ResourceNotFoundException("Blogs", "Blog Id", blogId));
		blog.setTitle(blogDto.getTitle());
		blog.setContent(blogDto.getContent());
		if(blog.getImageName()!= null)
			blog.setImageName(blogDto.getImageName());
		Blogs updatedBlog = this.blogRepo.save(blog);
		return this.modelmapper.map(updatedBlog, BlogDto.class);
	}

	@Override
	public void deleteBlog(Long blogId) {
		Blogs blog = this.blogRepo.findById(blogId)
				.orElseThrow(() -> new ResourceNotFoundException("Blogs", "Blog Id", blogId));
		this.blogRepo.delete(blog);
	}

	@Override
	public BlogUserCatResponse getBlogsByCategory(String catTitle) {
		Category cat = this.catRepo.findByTitle(catTitle);
		List<Blogs> blogs = this.blogRepo.findByCategory(cat);
		List<BlogDto> blogDtos = blogs.stream().map((blog) -> this.modelmapper.map(blog, BlogDto.class))
				.collect(Collectors.toList());
		BlogUserCatResponse br = new BlogUserCatResponse();
		br.setContent(blogDtos);
		return br;
	}

	@Override
	public BlogUserCatResponse getBlogsByUser(String userName) {
		User user = this.userRepo.findByUsername(userName);
		List<Blogs> blogs = this.blogRepo.findByUser(user);
		List<BlogDto> blogDtos = blogs.stream().map((blog) -> this.modelmapper.map(blog, BlogDto.class))
				.collect(Collectors.toList());
		BlogUserCatResponse br = new BlogUserCatResponse();
		br.setContent(blogDtos);
		return br;
	}

	@Override
	public List<BlogDto> searchBlog(String keyword) {
		List<Blogs> blogs = this.blogRepo.searchByTitle("%"+keyword+"%");
		List<BlogDto> blogDtos=blogs.stream().map((blog)->this.modelmapper.map(blog, BlogDto.class)).collect(Collectors.toList());
		return blogDtos;
	}

	@Override
	public BlogResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy) {
		Pageable p = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
		Page<Blogs> pageBlogs = this.blogRepo.findAll(p);
		List<Blogs> allBlogs = pageBlogs.getContent();
		List<BlogDto> blogDto = allBlogs.stream().map((blog) -> this.modelmapper.map(blog, BlogDto.class))
				.collect(Collectors.toList());
		BlogResponse blogResp = new BlogResponse();
		blogResp.setContent(blogDto);
		blogResp.setPageNumber(pageBlogs.getNumber());
		blogResp.setPageSize(pageBlogs.getSize());
		blogResp.setTotalElements(pageBlogs.getTotalElements());
		blogResp.setTotalpages(pageBlogs.getTotalPages());
		blogResp.setLastpage(pageBlogs.isLast());
		return blogResp;
	}

	@Override
	public BlogDto getBlogById(Long blogId) {
		Blogs blog = this.blogRepo.findById(blogId)
				.orElseThrow(() -> new ResourceNotFoundException("Blogs", "Blog Id", blogId));
		return this.modelmapper.map(blog, BlogDto.class);
	}

}
