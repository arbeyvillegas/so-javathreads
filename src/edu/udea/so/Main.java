package edu.udea.so;

import edu.udea.so.basic.HelloExecutor;
import edu.udea.so.basic.HelloRunnable;
import edu.udea.so.basic.HelloRunnableExecutor;
import edu.udea.so.basic.HelloThread;

public class Main {

    public static void main(String[] args) {
        //Hello with simple threads
        HelloRunnable.EjecuteThread();
        HelloThread.ExecuteThread();

        HelloRunnableExecutor helloRunnableExecutor = new HelloRunnableExecutor();
        HelloExecutor helloExecutor = new HelloExecutor();
        helloExecutor.execute(helloRunnableExecutor);
    }
}
