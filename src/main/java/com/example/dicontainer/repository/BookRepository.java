package com.example.dicontainer.repository;

import com.example.dicontainer.domain.Book;

public interface BookRepository {
    void restore(Book book);
    void rent(Long id);
}
