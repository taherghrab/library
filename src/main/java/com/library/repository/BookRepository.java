package com.library.repository;

import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAll(Pageable pageable);
    Page<Book> findByCategory_Name(String name, Pageable pageable);
    Page<Book> findByTitleContaining(String title, Pageable pageable);


}