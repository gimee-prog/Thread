package com.company;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Warming implements Runnable {
    private CountDownLatch latch;
    private BackSystem bs;
    Random random = new Random();

    public Warming(CountDownLatch latch, BackSystem bs) {
        this.latch = latch;
        this.bs = bs;
    }

    @Override
    public void run() {
        try {
            BackSystem.balance.addAndGet(random.nextInt(10000-5000)+5000);
            Thread.sleep(random.nextInt(10000-5000)+5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}
