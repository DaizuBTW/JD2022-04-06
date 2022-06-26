package by.it.kadulin.jd02_03.entity;

import by.it.kadulin.jd02_03.repository.PriceListRepo;

public class Good {
    private final String name;
    private final double price;


    public Good(String name, double price) {
        this.price = PriceListRepo.getPrice(name);
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "name " + name + " Price: " + price;
    }

    public Good() {
        name = "unknown good";
        price = 0;
    }
}
