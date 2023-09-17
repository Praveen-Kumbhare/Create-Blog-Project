package com.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.BlogDto;
import com.app.payloads.ApiResponse;
import com.app.payloads.BlogUserCatResponse;
import com.app.service.BlogService;
import com.app.service.FileService;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private FileService fileService;

	@Value(value = "${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/blogs")
	public ResponseEntity<?> createBlog(@RequestBody BlogDto Blogstransient, @PathVariable Long userId,
			@PathVariable Long categoryId) {
		return new ResponseEntity<>(blogService.createPost(Blogstransient, userId, categoryId), HttpStatus.CREATED);
	}

	@GetMapping("/user/{username}/blogs")
	public ResponseEntity<?> getBlogsByUser(@PathVariable String username) {
		BlogUserCatResponse blogs = this.blogService.getBlogsByUser(username);
		return new ResponseEntity<>(blogs, HttpStatus.OK);

	}

	@GetMapping("/category/{categoryId}/blogs")
	public ResponseEntity<?> getBlogsByCategory(@PathVariable String catTitle) {
		BlogUserCatResponse blogs = this.blogService.getBlogsByCategory(catTitle);
		return new ResponseEntity<>(blogs, HttpStatus.OK);

	}

	@GetMapping("/blogs/search")
	public ResponseEntity<?> getAllBlogs(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(name = "user", required = false) String username,
			@RequestParam(name = "cat", required = false) String catName) {
		if (username != null) {
			return this.getBlogsByUser(username);
		} else if (catName != null) {
			return this.getBlogsByCategory(catName);
		} else {

			return new ResponseEntity<>(this.blogService.getAllPost(pageNumber, pageSize, sortBy), HttpStatus.OK);
		}

	}

	@GetMapping("/blogs/{blog_id}")
	public ResponseEntity<?> getBlogById(@PathVariable Long blog_id) {
		return ResponseEntity.ok(this.blogService.getBlogById(blog_id));
	}

	@DeleteMapping("/blogs/{blog_id}")
	public ResponseEntity<?> deleteBlog(@PathVariable Long blog_id) {
		this.blogService.deleteBlog(blog_id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Blog Deleted Succefully", true), HttpStatus.OK);
	}

	@PutMapping("/blogs/{blog_id}")
	public ResponseEntity<?> updateCategory(@PathVariable @Valid Long blog_id, @RequestBody BlogDto request) {
		BlogDto updatedBlog = this.blogService.updateBlog(request, blog_id);
		return ResponseEntity.ok(updatedBlog);
	}

	@GetMapping("/blogs/search/{keywords}")
	public ResponseEntity<?> searchPostByTitle(@PathVariable String keywords) {
		List<BlogDto> result = this.blogService.searchBlog(keywords);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/blog/image/upload/{blogId}")
	public ResponseEntity<?> uploadBlogImage(@RequestParam("image") MultipartFile image, @PathVariable Long blogId)
			throws IOException {

		BlogDto blogDto = this.blogService.getBlogById(blogId);
		String fileName = this.fileService.uploadImage(path, image);
		blogDto.setImageName(fileName);
		BlogDto updateBlog = this.blogService.updateBlog(blogDto, blogId);

		return new ResponseEntity<>(updateBlog, HttpStatus.OK);

	}

	@GetMapping(value = "/blog/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());

	}
	

}
