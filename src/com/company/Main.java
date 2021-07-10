package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        FrontSystem fs = new FrontSystem();
        BackSystem bs = new BackSystem();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ExecutorService executorClient = Executors.newFixedThreadPool(5);
        ExecutorService executorTreatment = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 3; i++)
            executorService.submit(new Warming(latch,bs));
        executorService.shutdown();
        latch.await();

        executorClient.submit(new Client(1, fs, 5000, "CREDIT"));
        executorClient.submit(new Client(2, fs, 6000, "PAYMENT"));
        executorClient.submit(new Client(3, fs, 7000, "CREDIT"));
        executorClient.submit(new Client(4, fs, 500, "PAYMENT"));
        executorClient.submit(new Client(5, fs, 9000, "CREDIT"));
        executorClient.shutdown();
        executorTreatment.submit(new Treatment(1, fs, bs));
        executorTreatment.submit(new Treatment(2, fs, bs));
        executorTreatment.shutdown();

    }
}
