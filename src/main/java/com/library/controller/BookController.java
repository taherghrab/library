package com.library.controller;
import com.library.model.Category;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Book add(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.getById(id);
    }
    @GetMapping("/page")
    public Page<Book> getPage(Pageable pageable) {
        return bookService.getPage(pageable);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        return bookService.update(id, book);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
    @GetMapping("/search")
    public Page<Book> search(@RequestParam String title, Pageable pageable) {
        return bookService.searchByTitle(title, pageable);
    }
    @GetMapping("/category")
    public Page<Book> byCategory(@RequestParam String name, Pageable pageable) {
        return bookService.searchByCategory(name, pageable);
    }


}