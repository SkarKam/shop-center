package com.solvd.laba.threads;

public class MyRunnableThread implements Runnable {

    @Override
    public void run() {
        try{
            System.out.println("MyRunnableThread "+Thread.currentThread().getName() + ": Connected to the database");
            Thread.sleep(3000);
            System.out.println("MyRunnableThread "+Thread.currentThread().getName() + ": Connection released");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
