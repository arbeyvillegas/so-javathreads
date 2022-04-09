package edu.udea.so.synchronizedLock;

public class HelloSynchronized{

    private int count = 0;

    public int inc(){
        synchronized(this){
            System.out.println("Hello from synchronized method");
            return ++count;
        }
    }
}