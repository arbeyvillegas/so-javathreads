package edu.udea.so.conditional;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWithCondition {

    Stack<String> stack = new Stack<>();
    int CAPACITY = 5;

    ReentrantLock lock = new ReentrantLock();
    Condition stackEmptyCondition = lock.newCondition();
    Condition stackFullCondition = lock.newCondition();

    public void pushToStack(String item)  {
        try {
            lock.lock();
            while(stack.size() == CAPACITY) {
                System.out.println("Stack is full, waiting for pop");
                stackFullCondition.await();
            }
            System.out.println("Pushing item " + item);
            stack.push(item);
            System.out.println("Stack size is " + stack.size());
            stackEmptyCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String popFromStack() {
        try {
            lock.lock();
            while(stack.size() == 0) {
                System.out.println("Stack is empty, waiting for push");
                stackEmptyCondition.await();
            }
            System.out.println("Popping item " + stack.peek());
            return stack.pop();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            System.out.println("Stack size is " + stack.size());
            stackFullCondition.signalAll();
            lock.unlock();
        }
    }
}