package edu.udea.so.lock;


import sun.misc.Lock;

public class HelloLock {

    private Lock lock = new Lock();
    private int count = 0;

    public int start() throws InterruptedException {
        lock.lock();
        System.out.println("Executing sentence from lock thread");
        int newCount = ++count;
        lock.unlock();
        return newCount;
    }
}