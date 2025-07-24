package com.zky.fragments.raceCondition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

public class CacheRaceConditionTest {
    private static class CacheService {
        private final Map<String, String> cache = new HashMap<>();

        public String getOrLoad(String key) {
            if (!cache.containsKey(key)) {
                // 模拟耗时的加载操作
                cache.put(key, "value_" + key);
            }
            return cache.get(key);
        }
    }

    @Test
    public void testRaceCondition() throws InterruptedException {
        final int iterations = 1000;
        CacheService service = new CacheService();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CyclicBarrier barrier = new CyclicBarrier(2); // 两个线程同步

        Runnable task = () -> {
            try {
                for (int i = 0; i < iterations; i++) {
                    barrier.await(); // 同步点，增加竞争概率
                    service.getOrLoad("key");
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
        };

        executor.submit(task);
        executor.submit(task);

        executor.shutdown();
        executor.awaitTermination(1, java.util.concurrent.TimeUnit.SECONDS);

        // 打印缓存大小，预期为 1，但竞态条件可能导致多次加载
        // 实际也一直都是 1，可能是我的电脑性能太好了？
        assertEquals(1, service.cache.size());
    }
}
