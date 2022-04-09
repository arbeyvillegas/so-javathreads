package edu.udea.so;

import edu.udea.so.basic.HelloExecutor;
import edu.udea.so.basic.HelloRunnable;
import edu.udea.so.basic.HelloRunnableExecutor;
import edu.udea.so.basic.HelloThread;
import edu.udea.so.conditional.ReentrantLockWithCondition;
import edu.udea.so.lock.HelloLock;
import edu.udea.so.others.SharedObject;
import edu.udea.so.others.StampedLockDemo;
import edu.udea.so.others.SynchronizedHashMapWithReadWriteLock;
import edu.udea.so.semaphore.MySemaphore;
import edu.udea.so.semaphore.Shared;
import edu.udea.so.synchronizedLock.HelloSynchronized;
import sun.misc.Lock;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        //Menu
        System.out.println("1. Hello Thread");
        System.out.println("2. Hello Runnable");
        System.out.println("3. Hello Executor");
        System.out.println("4. Hello Synchronized");
        System.out.println("5. Hello Lock");
        System.out.println("6. Conditional");
        System.out.println("7. Semaphore");
        System.out.println("8. Other 1: ReentrantLock");
        System.out.println("9. Other 2: ReadWriteLock");
        System.out.println("10. Other 3: StampedLock");
        System.out.println("0. Exit");


        //Seleccionar opcion scanner
        int choice = 1;


        //Ejecutar opcion

        while (choice != 0) {
            System.out.println("Select an option: ");
            try {
                choice = new Scanner(System.in).nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Sorry, your input should be an integer. Try again.");
                e.printStackTrace();
            }
            switch (choice) {
                case 1:
                    HelloThread ht = new HelloThread();
                    ht.start();
                    break;
                case 2:
                    HelloRunnable hr = new HelloRunnable();
                    Thread t = new Thread(hr);
                    t.start();
                    break;
                case 3:
                    HelloRunnableExecutor helloRunnableExecutor = new HelloRunnableExecutor();
                    HelloExecutor helloExecutor = new HelloExecutor();
                    helloExecutor.execute(helloRunnableExecutor);
                    break;
                case 4:
                    HelloSynchronized helloSynchronized = new HelloSynchronized();
                    helloSynchronized.inc();
                    break;
                case 5:
                    HelloLock helloLock = new HelloLock();
                    System.out.println("Hello lock: " + helloLock.start());
                    break;
                case 6:
                    Lock lock = new Lock();
                    lock.lock();
                    ReentrantLockWithCondition reentrantLockWithCondition = new ReentrantLockWithCondition();

                    reentrantLockWithCondition.pushToStack("Hello");
                    reentrantLockWithCondition.pushToStack("World");
                    reentrantLockWithCondition.pushToStack("!");
                    reentrantLockWithCondition.pushToStack("!");
                    reentrantLockWithCondition.pushToStack("!");
                    reentrantLockWithCondition.pushToStack("!");
                    reentrantLockWithCondition.popFromStack();
                    lock.unlock();
                    break;
                case 7:
                    // creating a Semaphore object
                    // with number of permits 1
                    Semaphore sem = new Semaphore(1);

                    // creating two threads with name A and B
                    // Note that thread A will increment the count
                    // and thread B will decrement the count
                    MySemaphore mt1 = new MySemaphore(sem, "A");
                    MySemaphore mt2 = new MySemaphore(sem, "B");

                    // stating threads A and B
                    mt1.start();
                    mt2.start();

                    // waiting for threads A and B
                    mt1.join();
                    mt2.join();

                    // count will always remain 0 after
                    // both threads will complete their execution
                    System.out.println("count: " + Shared.count);
                    break;
                case 8:
                    SharedObject sharedObject = new SharedObject();

                    sharedObject.perform();
                    break;
                case 9:
                    StampedLockDemo stampedLockDemo = new StampedLockDemo();

                    stampedLockDemo.readWithOptimisticLock("Hello");
                    stampedLockDemo.put("Hello", "World");
                    stampedLockDemo.readWithOptimisticLock("Hello");
                    stampedLockDemo.get("Hello");
                    break;
                case 10:
                    SynchronizedHashMapWithReadWriteLock synchronizedHashMapWithReadWriteLock = new SynchronizedHashMapWithReadWriteLock();

                    synchronizedHashMapWithReadWriteLock.put("Hello", "World");
                    synchronizedHashMapWithReadWriteLock.containsKey("Hello");
                    synchronizedHashMapWithReadWriteLock.get("Hello");
                    synchronizedHashMapWithReadWriteLock.remove("Hello");
                    synchronizedHashMapWithReadWriteLock.containsKey("Hello");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;

            }
        }
    }
}
