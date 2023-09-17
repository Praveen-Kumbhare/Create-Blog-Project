package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.CategoryDto;
import com.app.entities.Category;
import com.app.repository.CategoryRepository;

@Service
@Transactional
public class CategoriesServiceimp implements CategoryService {
	@Autowired
	private CategoryRepository catRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.dtoToCategory(categoryDto);
		Category savedCategory = this.catRepo.save(category);
		return this.CategoryToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		Category category = this.catRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", " id ", categoryId));
		category.setDescription(categoryDto.getDescription());
		category.setTitle(categoryDto.getTitle());
		Category cat = this.catRepo.save(category);
		return this.CategoryToDto(cat);
	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category category = this.catRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", " id ", categoryId));
		this.catRepo.delete(category);
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId) {
		Category category = this.catRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", " id ", categoryId));
		return this.CategoryToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = this.catRepo.findAll();
		List<CategoryDto> userDtos = categories.stream().map(cat -> this.CategoryToDto(cat))
				.collect(Collectors.toList());
		return userDtos;
	}

	public Category dtoToCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		return category;
	}

	public CategoryDto CategoryToDto(Category category) {
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
