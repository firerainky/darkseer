package com.zky.application.async;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AsyncServiceTests {

    @Autowired
    private AsyncServiceImpl sut;

    @Test
    public void notifyUser() {
        doSomething();
        System.out.println("Something good happened");
    }

    private void doSomething() {
        sut.notifyUser();
        System.out.println("Something better happened");
    }
}
