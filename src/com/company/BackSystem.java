package com.company;

import java.util.concurrent.atomic.AtomicInteger;

public class BackSystem extends Thread {
    int treatmentId;
     static AtomicInteger balance= new AtomicInteger(0);

    public void treatmentRequest(Request request) {

        if (request.getRequestType() == "CREDIT"){
            if (balance.get() < request.getAmount()){
                System.out.println( "Заявка №"+request.getId()+": не выполнена. " +
                        "На счету банка недостаточно средств. Баланс банка= " + balance);
                return;
            } else {
                balance.addAndGet(-request.getAmount());
                System.out.println(" Заявка №"+request.getId()+": выполнена. Баланс банка= "+balance);
            }
        }
        if (request.getRequestType() == "PAYMENT"){
            balance.addAndGet(request.getAmount());
            System.out.println("Заявка №"+request.getId()+": выполнена. Баланс банка= "+balance);
        }

    }
}
