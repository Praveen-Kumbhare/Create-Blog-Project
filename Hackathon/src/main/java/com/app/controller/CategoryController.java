package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CategoryDto;
import com.app.payloads.ApiResponse;
import com.app.service.CategoryService;

@RestController
@RequestMapping("/api/categories/")
@CrossOrigin
public class CategoryController {

	@Autowired
	private CategoryService catService;

	@PostMapping("/")
	public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDto request) {
		return new ResponseEntity<>(catService.createCategory(request), HttpStatus.OK);
	}

	@PutMapping("/{category_id}")
	public ResponseEntity<?> updateCategory(@PathVariable  @Valid Long category_id, @RequestBody CategoryDto request) {
		CategoryDto updateCategory = this.catService.updateCategory(request, category_id);
		return ResponseEntity.ok(updateCategory);
	}

	@DeleteMapping("/{category_id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long category_id) {
		this.catService.deleteCategory(category_id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Succefully", true), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllCategories() {
		return ResponseEntity.ok(this.catService.getAllCategories());
	}

	@GetMapping("/{category_id}")
	public ResponseEntity<?> getCategoryById(@PathVariable Long category_id) {
		return ResponseEntity.ok(this.catService.getCategoryById(category_id));
	}
}
