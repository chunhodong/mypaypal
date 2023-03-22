package com.example.dicontainer.repository;

import com.example.dicontainer.domain.Book;

public interface BookRepository {
    void restore(Book book);
    Book rent(Long id);
}
