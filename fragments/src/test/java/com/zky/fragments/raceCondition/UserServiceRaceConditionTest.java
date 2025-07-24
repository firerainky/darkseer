package com.zky.fragments.raceCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

public class UserServiceRaceConditionTest {
    private static class UserService {
        private final List<String> userCache = new ArrayList<>();

        public void addUser(String username) {
            // 模拟检查与插入操作之间的延迟
            try {
                Thread.sleep(10); // 增加竞争窗口
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (!userCache.contains(username)) {
                userCache.add(username);
            }
        }

        public List<String> getUsers() {
            return userCache;
        }
    }

    @Test
    public void testRaceCondition() throws InterruptedException {
        UserService service = new UserService();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 两个线程尝试添加相同用户
        executor.submit(() -> service.addUser("test"));
        executor.submit(() -> service.addUser("test"));

        executor.shutdown();
        executor.awaitTermination(1, java.util.concurrent.TimeUnit.SECONDS);

        // 由于竞态条件，userCache 可能包含重复的 "test"
        // 实际不会，可能是我的电脑性能太好了？
        System.out.println("Users: " + service.getUsers());
    }
}
