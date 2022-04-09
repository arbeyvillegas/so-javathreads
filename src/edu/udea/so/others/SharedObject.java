package edu.udea.so.others;

import java.util.concurrent.locks.ReentrantLock;

public class SharedObject {
    //...
    ReentrantLock lock = new ReentrantLock();
    int counter = 0;

    public void perform() {
        lock.lock();
        try {
            // Critical section here
            System.out.println("Hello from thread " + Thread.currentThread().getName());
            System.out.println("From reentrant lock");
            counter++;
        } finally {
            lock.unlock();
        }
    }
    //...
}