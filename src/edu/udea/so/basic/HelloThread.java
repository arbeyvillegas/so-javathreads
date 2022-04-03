package edu.udea.so.basic;

public class HelloThread extends Thread {
    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void ExecuteThread() {
        (new HelloThread()).start();
    }
}
