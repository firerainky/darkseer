package com.zky.fragments.mythread;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;

class MyThreadTestSubclass {

    private CountDownLatch latch = new CountDownLatch(2);

    @Test
    void testMyThreadExecution() throws InterruptedException {
        // The anonymous inner class approach is better in this case and we can check that approach in MyTestThread file.
        MyTestThread thread1 = new MyTestThread("Thread 1", latch);
        MyTestThread thread2 = new MyTestThread("Thread 2", latch);

        thread1.start();
        thread2.start();

        latch.await();

        assertFalse(thread1.isAlive() || thread2.isAlive(), "Both threads should have completed.");
    }
}

