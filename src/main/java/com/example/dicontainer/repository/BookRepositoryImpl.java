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
    public void rent(Long id) {
        books.remove(id);
    }
}
