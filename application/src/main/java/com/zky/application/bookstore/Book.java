package com.zky.application.bookstore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Book {

    @Getter
    private Long id;
    @Getter
    private String title;
    @Getter
    private Author author;
    @Getter
    private String description;
    @Getter
    private Double price;

    public void updatePrice(Double price) {
        this.price = price;
    }

    public void changeDescription(String description) {
        this.description = description;
    }
}
