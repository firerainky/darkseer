package com.zky.fragments.mythread;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;

class MyThreadTest {

    private CountDownLatch latch = new CountDownLatch(2);

    @Test
    public void testMyThreadExecution() throws InterruptedException {
        // The thread creation technique called anonymous inner class.
        // This is a common approach in Java to customize the behavior a class without needing to create a seperate subclass.
        // In this scenario, we use this mechanism to encapsulate the behavior of the thread within the test method.
        // This approach is simple, flexible and easy to maintain compared to creating a seperate subclass.
        MyThread thread1 = new MyThread("Thread 1") {
            @Override
            public void run() {
                // Using super.run to call the parent class's run method
                super.run();
                latch.countDown(); // Decrement the latch count
            }
        };
        MyThread thread2 = new MyThread("Thread 2") {
            @Override
            public void run() {
                super.run();
                latch.countDown(); // Decrement the latch count
            }
        };

        // Start the threads
        thread1.start();
        thread2.start();

        // Wait for both threads to finish
        latch.await();

        // You can add assertions here if you store results in a shared resource
        // For now, just checking that we reached this point without exceptions
        assertFalse(thread1.isAlive() || thread2.isAlive(), "Both threads should have completed.");
    }
}
