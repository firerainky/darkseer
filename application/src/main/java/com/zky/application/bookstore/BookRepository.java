package com.zky.application.bookstore;

import java.util.List;

public class BookRepository {

    private List<Book> books;

    public void addBookToCatalog(Catalog catalog, Book book) {
        catalog.addBook(book);
    }

    public void removeBookFromCatalog(Catalog catalog, Book book) {
        catalog.removeBook(book);
    }

    public List<Book> findBooksByAuthor(Author author) {
        return books.stream().filter(book -> book.getAuthor().equals(author)).toList();
    }
}
