package edu.udea.so.others;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {
    Map<String,String> map = new HashMap<>();
    private StampedLock lock = new StampedLock();

    public void put(String key, String value){
        long stamp = lock.writeLock();
        try {
            System.out.println("Write lock acquired");
            System.out.println("Writing value: " + value);
            map.put(key, value);
        } finally {
            System.out.println("Write lock released");
            lock.unlockWrite(stamp);
        }
    }

    public String get(String key) throws InterruptedException {
        long stamp = lock.readLock();
        try {
            System.out.println("Read lock acquired");
            System.out.println("Reading value: " + map.get(key));
            return map.get(key);
        } finally {
            System.out.println("Read lock released");
            lock.unlockRead(stamp);
        }
    }

    public String readWithOptimisticLock(String key) {
        long stamp = lock.tryOptimisticRead();
        String value = map.get(key);

        if(!lock.validate(stamp)) {
            System.out.println("Optimistic read failed");
            stamp = lock.readLock();
            try {
                System.out.println("Read lock acquired");
                System.out.println("Reading value: " + map.get(key));
                return map.get(key);
            } finally {
                System.out.println("Read lock released");
                lock.unlock(stamp);
            }
        }
        return value;
    }
}