package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;
    public  Book save(Book book) {

        if (book.getCategory() != null && book.getCategory().getId() != null) {

            Long categoryId = book.getCategory().getId();

            book.setCategory(
                    categoryRepository.findById(categoryId).orElse(null)
            );
        }

        return bookRepository.save(book);
    }
    public Page<Book> getPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public Book update(Long id, Book book) {
        Book existing = getById(id);

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setDescription(book.getDescription());
        existing.setImage(book.getImage());
        if (book.getCategory() != null && book.getCategory().getId() != null) {

            Long categoryId = book.getCategory().getId();

            existing.setCategory(
                    categoryRepository.findById(categoryId).orElse(null)
            );
        }

        return bookRepository.save(existing);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    public Page<Book> searchByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitleContaining(title, pageable);
    }

    public Page<Book> searchByCategory(String name, Pageable pageable) {
        return bookRepository.findByCategory_Name(name, pageable);
    }
}