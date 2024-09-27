package com.solvd.laba.threads;


public class MockConnection {

    private String name;

    public MockConnection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void testMethod() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " using connection: " + name);
        Thread.sleep(5000);
    }
    @Override
    public String toString() {
        return  name;
    }
}
