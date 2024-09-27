package com.solvd.laba.threads;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {

    private static volatile ConnectionPool instance;
    private int poolSize;
    private BlockingDeque<MockConnection> usedConnections;


    public ConnectionPool(int poolSize) {
        this.poolSize = poolSize;
        usedConnections = new LinkedBlockingDeque<MockConnection>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            usedConnections.add(new MockConnection("Connection-" + i));
        }
    }

    public synchronized ConnectionPool getInstance(int poolSize) {
        if(instance == null){
            instance = new ConnectionPool(poolSize);
        }
        return instance;
    }

    public MockConnection getConnection() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " waiting for connection");
        return usedConnections.take();
    }

    public void releaseConnection(MockConnection connection){
        System.out.println(Thread.currentThread().getName() + " releasing connection" + connection.getName());
        usedConnections.offer(connection);
    }
}

