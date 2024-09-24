package com.zky.fragments.mythread;

import java.util.concurrent.CountDownLatch;

class MyTestThread extends MyThread {
    private CountDownLatch latch;

    public MyTestThread(String name, CountDownLatch latch) {
        super(name);
        this.latch = latch;
    }

    @Override
    public void run() {
        super.run();
        latch.countDown();
    }
}
