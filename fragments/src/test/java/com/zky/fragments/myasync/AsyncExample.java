package com.zky.fragments.myasync;

import java.util.concurrent.CompletableFuture;

class AsyncExample {

    private PlusOneCallback completion;

    int number = 0;

    AsyncExample(PlusOneCallback completion) {
        this.completion = completion;
    }

    int syncTask() {
        return completion.plus(1);
    }

    void asyncTask() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> completion.plus(1));
        future.thenAccept(result -> {
            System.out.println("Result: " + result);
            number += result;
        });

        // future.join();
        System.out.println("Async task completed");
    }
}
