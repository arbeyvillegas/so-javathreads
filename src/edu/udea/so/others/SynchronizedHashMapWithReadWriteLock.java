package edu.udea.so.others;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SynchronizedHashMapWithReadWriteLock {

    Map<String, String> syncHashMap = new HashMap<>();
    ReadWriteLock lock = new ReentrantReadWriteLock();
    // ...
    Lock writeLock = lock.writeLock();
    Lock readLock = lock.readLock();

    //...
    public void put(String key, String value) {
        try {
            writeLock.lock();
            System.out.println("writeLock locked");
            syncHashMap.put(key, value);
            System.out.println("put: " + key + " " + value);
        } finally {
            System.out.println("Write lock released");
            writeLock.unlock();
        }
    }

    public String get(String key) {
        try {
            System.out.println("readLock locked");
            readLock.lock();
            System.out.println("get: " + key + " " + syncHashMap.get(key));
            return syncHashMap.get(key);
        } finally {
            System.out.println("Read lock released");
            readLock.unlock();
        }
    }

    public boolean containsKey(String key) {
        try {
            System.out.println("readLock locked");
            readLock.lock();
            System.out.println("containsKey: " + key + " " + syncHashMap.containsKey(key));
            return syncHashMap.containsKey(key);
        } finally {
            System.out.println("Read lock released");
            readLock.unlock();
        }
    }

    public String remove(String key) {
        try {
            System.out.println("writeLock locked");
            writeLock.lock();
            System.out.println("remove: " + key + " " + syncHashMap.remove(key));
            return syncHashMap.remove(key);
        } finally {
            System.out.println("Write lock released");
            writeLock.unlock();
        }
    }
    //...
}