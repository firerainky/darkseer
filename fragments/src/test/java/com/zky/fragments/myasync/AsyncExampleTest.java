package com.zky.fragments.myasync;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AsyncExampleTest {

    @Test
    void test_syncTask_shouldReturnCorrectly() {
        PlusOneStub callback = new PlusOneStub();
        AsyncExample example = new AsyncExample(callback);

        assertEquals(2, example.syncTask());
        assertEquals(1, callback.callCount);
    }

    @Test
    void test_asyncTask_shouldReturnCorrectly() throws InterruptedException {
        PlusOneStub callback = new PlusOneStub();
        AsyncExample example = new AsyncExample(callback);

        example.asyncTask();

        Thread.sleep(100);

        assertEquals(1, callback.callCount);
        assertEquals(2, example.number);
    }

    @Test
    void test_nonBlockingAsyncTask() throws InterruptedException {
        PlusOneStub callback = new PlusOneStub();
        AsyncExample example = new AsyncExample(callback);

        example.nonBlockingAsyncTask();

        // Thread.sleep(100);

        // assertEquals(1, callback.callCount);
    }

    class PlusOneStub implements PlusOneCallback {
        int callCount = 0;

        @Override
        public int plus(int param) {
            callCount += 1;
            return param + 1;
        }
    }
}