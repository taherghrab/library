package com.library.service;

import com.library.model.Category;
import com.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public Category update(Long id, Category category) {
        Category existing = getById(id);

        existing.setName(category.getName());

        return categoryRepository.save(existing);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}