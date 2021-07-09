package com.company;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FrontSystem {

   static BlockingQueue<Request> requestQueue = new ArrayBlockingQueue<>(2);
//Очередь с фиксированным размером, блокирует добавление новых элементов если очередь полна и удаление если пуста
    public  void addRequest(Request request) throws InterruptedException {
            requestQueue.put(request);
            System.out.println("Клиент №" + request.getId() + ":Заявка №" + request.getId() + " принята банком" + "{" + request.getName() + ", сумма= " + request.getAmount() + " , тип операции: " + request.getRequestType());
            }

    public  Request getRequest() throws InterruptedException {
     return requestQueue.take();
    }
}
