package edu.udea.so.basic;

public class HelloRunnable implements Runnable {
    public void run() {
        System.out.println("Hello from a runnable!");
    }

    public static void EjecuteThread() {
        (new Thread(new HelloRunnable())).start();
    }
}
