package com.zky.application.async;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AsyncServiceTests {

    @Autowired
    private AsyncService sut;

    @Test
    public void notifyUser() {
        sut.notifyUser();
    }
}
