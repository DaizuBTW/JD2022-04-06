package by.it.ragach.jd02_02.service;


import by.it.ragach.jd02_02.entity.*;
import by.it.ragach.jd02_02.util.RandomGenerator;
import by.it.ragach.jd02_02.util.Timer;

import java.util.Map;
import java.util.Objects;

public class CashierWorker implements Runnable {

    private final Cashier cashier;
    private final Shop shop;

    public CashierWorker(Cashier cashier, Shop shop) {
        this.cashier = cashier;
        this.shop = shop;
    }


    @Override
    public void run() {
        System.out.println(cashier + " opened");
        Manager manager = shop.getManager();
        Queue queue = shop.getQueue();
        while (!manager.shopClosed()) {
            Customer customer = queue.extract();
            if (Objects.nonNull(customer)) {
                synchronized (customer.getMonitor()) {
                    serveCustomer(customer);
                    customer.setWaiting(false);
                    customer.notify();
                }
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println(cashier + " closed. Total revenue: " + cashier.getRevenue());
    }

    private void serveCustomer(Customer customer) {
        System.out.println(cashier + " started service " + customer);
        int timeout = RandomGenerator.get(2000, 5000);
        Timer.sleep(timeout);
        System.out.println(cashier + " finished service " + customer);
    }


}
