package com.example.dicontainer.service;

import com.example.dicontainer.domain.Book;
import com.example.dicontainer.repository.BookRepository;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book get(Long id) {
        return bookRepository.get(id);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }
}
