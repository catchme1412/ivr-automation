package com.automation.ivr.core.input;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.automation.ivr.exception.VxmlNoInputEventException;

public class ConsoleInput {

    private final int timeout;
    private final TimeUnit unit;

    public ConsoleInput(int timeout, TimeUnit unit) {
        this.timeout = timeout;
        this.unit = unit;
    }

    public String readLine() throws VxmlNoInputEventException {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        String input = null;
        try {
            // start working
//            for (int i = 0; i < tries; i++) {
//                System.out.println(String.valueOf(i + 1) + ". loop");
                Future<String> result = ex.submit(new ConsoleInputReadTask());
                try {
                    input = result.get(timeout, unit);
                    System.out.println("input received: " + input);
                } catch (ExecutionException e) {
                    e.getCause().printStackTrace();
                } catch (TimeoutException e) {
                    System.out.println("Cancelling reading task");
                    result.cancel(true);
                    System.out.println("\nThread cancelled. input is null");
                    throw new VxmlNoInputEventException(e);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        } finally {
            ex.shutdownNow();
        }
        return input;
    }

    public static void main(String[] args) throws InterruptedException, VxmlNoInputEventException {
        new ConsoleInput(5, TimeUnit.SECONDS).readLine();
    }

}
