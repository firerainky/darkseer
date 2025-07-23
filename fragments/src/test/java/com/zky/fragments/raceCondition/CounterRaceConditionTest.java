package com.zky.fragments.raceCondition;

import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CounterRaceConditionTest {
    private static class Counter {
        private int count = 0;

        public void increment() {
            count++; // 非线程安全操作
        }

        public int getCount() {
            return count;
        }
    }

    @Test
    public void testRaceCondition() throws InterruptedException {
        final int threadCount = 1000;
        Counter counter = new Counter();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(1); // 用于同步所有线程同时开始

        // 提交多个线程执行 increment()
        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    latch.await(); // 等待统一开始信号
                    counter.increment();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 发出开始信号
        latch.countDown();
        executor.shutdown();

        // 等待所有线程执行完毕
        while (!executor.isTerminated()) {
            Thread.sleep(100);
        }

        // 预期结果应为 1000，但由于竞态条件可能小于 1000
        System.out.println("Final count: " + counter.getCount());
    }
}
