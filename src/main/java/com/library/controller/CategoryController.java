package com.library.controller;

import com.library.model.Category;
import com.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // ✔ GET ALL
    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    // ✔ GET BY ID
    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    // ✔ ADD (ADMIN)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Category add(@RequestBody Category category) {
        return categoryService.save(category);
    }

    // ✔ UPDATE (ADMIN)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Category update(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    // ✔ DELETE (ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}