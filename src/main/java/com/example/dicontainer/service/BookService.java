package com.example.dicontainer.service;

import com.example.dicontainer.domain.Book;
import com.example.dicontainer.repository.BookRepository;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book rent(Long id) {
        return bookRepository.rent(id);
    }

    public void restore(Book book) {
        bookRepository.restore(book);
    }
}
