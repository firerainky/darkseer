package com.zky.application.bookstore;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Catalog {

    @Getter
    private String name;
    @Getter
    private List<Book> books;

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }
}
