package by.it.ragach.jd02_01.service;

import by.it.ragach.jd02_01.entity.PriceListRepo;
import by.it.ragach.jd02_01.util.RandomGenerator;
import by.it.ragach.jd02_01.entity.ShoppingCart;
import by.it.ragach.jd02_01.util.Timer;
import by.it.ragach.jd02_01.entity.Customer;
import by.it.ragach.jd02_01.entity.Good;
import by.it.ragach.jd02_01.entity.Shop;
import by.it.ragach.jd02_01.interfaces.CustomerAction;
import by.it.ragach.jd02_01.interfaces.ShoppingCardAction;

public class CustomerWorker extends Thread implements CustomerAction, ShoppingCardAction {

    private final Customer customer;
    private final Shop shop;
    private ShoppingCart shoppingCart;


    public CustomerWorker(Customer customer, Shop shop) {
        this.customer = customer;
        this.shop = shop;
    }

    @Override
    public void run() {
        enteredStore();
        takeCart();
        int amountOfGoods = RandomGenerator.get(2,5);
        for (int i= 0; i < amountOfGoods;i++) {
            Good good = chooseGood();
            putToCart(good);
            
        }
        System.out.println(customer + " stopped to choose goods");
        goOut();
    }

    @Override
    public void enteredStore() {
        System.out.println(customer + " enter to the" + shop);

    }

    @Override
    public void takeCart() {
        this.shoppingCart = new ShoppingCart();
        int timeout = RandomGenerator.get(100, 300);
        Timer.sleep(timeout);
        System.out.println(customer + " takes shopping cart");

    }

    @Override
    public Good chooseGood() {
        System.out.println(customer + " started to choose goods");
        int timeout = RandomGenerator.get(500, 2000);
        Timer.sleep(timeout);
        String nameGoods = PriceListRepo.getGoodName();
        double price = PriceListRepo.getPrice(nameGoods);
        Good good = new Good(nameGoods, price);
        System.out.println(customer + " choose " + good);
        System.out.println(customer + " finished to choose goods");
        return good;
    }



    @Override
    public void goOut() {
        System.out.println(customer + " leaves the" + shop);

    }

    @Override
    public int putToCart(Good good) {
        shoppingCart.addGoods(good.getName(), good.getPrice());
        int timeout = RandomGenerator.get(100,300);
        Timer.sleep(timeout);
        return shoppingCart.size();

    }
}
