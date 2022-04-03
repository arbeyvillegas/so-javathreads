package edu.udea.so.basic;

public class HelloRunnableExecutor implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello from a executor!");
    }
}
