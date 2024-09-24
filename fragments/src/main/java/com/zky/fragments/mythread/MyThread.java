package com.zky.fragments.mythread;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;

@Slf4j
class MyThread extends Thread {

    private String name;

    MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        log.info(name + " is running");
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        list.forEach(i -> {
            log.info(name + " is working at " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.info(name + "was interrupted.");;
            }
        });
        log.info(name + " has finished.");
    }
}
