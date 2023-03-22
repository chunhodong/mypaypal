package com.example.dicontainer.repository;

import com.example.dicontainer.domain.Book;

public interface BookRepository {
    void save(Book book);
    Book get(Long id);
}
