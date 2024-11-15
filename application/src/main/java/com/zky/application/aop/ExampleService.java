package com.zky.application.aop;

import org.springframework.stereotype.Service;

@Service
public class ExampleService {
    
    public void doSomething() {
        // This method execution will be logged
        try {
            Thread.sleep(1000); // Simulate some work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
} 