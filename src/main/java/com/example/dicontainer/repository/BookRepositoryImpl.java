package com.example.dicontainer.repository;

import com.example.dicontainer.domain.Book;

import java.util.HashMap;
import java.util.Map;

public class BookRepositoryImpl implements BookRepository {
    private static final Map<Long, Book> books = new HashMap<>();

    @Override
    public void restore(Book book) {
        books.put(book.getId(), book);
    }

    @Override
    public Book rent(Long id) {
        return books.remove(id);
    }
}
