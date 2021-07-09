package com.company;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Warming implements Runnable {
    private CountDownLatch latch;
    Random random = new Random();

    public Warming(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("Thread well done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}
