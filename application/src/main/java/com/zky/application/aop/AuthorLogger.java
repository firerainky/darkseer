package com.zky.application.aop;

import org.springframework.stereotype.Component;

@Component
public class AuthorLogger {
    public void logAuthor(String author) {
        System.out.println("Author: " + author);
    }
}
