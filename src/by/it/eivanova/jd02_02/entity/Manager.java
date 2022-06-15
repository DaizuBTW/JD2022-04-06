package by.it.eivanova.jd02_02.entity;

public class Manager {
    private final int plan; //100
    private volatile int countIn;
    private volatile int countOut;

    public Manager(int plan) {
        this.plan = plan;
    }

    public boolean shopOpened() {
        return countIn != plan;
    }

    public boolean shopClosed() {
        return countOut == plan;
    }

    public synchronized void customerEnter(){
        countIn++;
    }

    public synchronized void customerOut(){
        countOut++;
    }
}
