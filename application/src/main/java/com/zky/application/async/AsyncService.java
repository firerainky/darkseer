package com.zky.application.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncService {

    public void notifyUser() {
        // Basic stuff
        System.out.println("Basic stuff done");

        // Call the async method for time-consuming task
        performTimeConsumingTask();
    }

    @Async
    public void performTimeConsumingTask() {
        // Simulate a long-running task
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Time-consuming task completed");
    }
}
