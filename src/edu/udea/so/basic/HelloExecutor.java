package edu.udea.so.basic;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloExecutor implements Executor {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    @Override
    public void execute(Runnable command) {
        executor.execute(command);
        executor.shutdown();
    }
}
