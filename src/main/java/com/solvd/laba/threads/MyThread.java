package com.solvd.laba.threads;

public class MyThread extends Thread {

    public void run() {
        try{
            System.out.println("MyThread "+Thread.currentThread().getName() + " Connected to the database");
            Thread.sleep(3000);
            System.out.println("MyThread "+Thread.currentThread().getName() + ": Connection released");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
