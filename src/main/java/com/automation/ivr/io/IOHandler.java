package com.automation.ivr.io;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import com.automation.ivr.core.OutputWrapper;
import com.automation.ivr.core.input.ConsoleInput;
import com.automation.ivr.exception.VxmlNoInputEventException;

public class IOHandler {

    private Queue<OutputWrapper> outputQueue;
    private Queue<String> dtmfInputQueue;

    public IOHandler() {
        outputQueue = new LinkedBlockingDeque<OutputWrapper>();
        dtmfInputQueue = new LinkedBlockingDeque<String>();
    }

    public Queue<OutputWrapper> getOutputQueue() {
        return outputQueue;
    }

    public void setOutputQueue(Queue<OutputWrapper> outputQueue) {
        this.outputQueue = outputQueue;
    }

    public Queue<String> getDtmfInputQueue() {
        return dtmfInputQueue;
    }

    public void setDtmfInputQueue(Queue<String> dtmfInputQueue) {
        this.dtmfInputQueue = dtmfInputQueue;
    }

    public String readInput() throws VxmlNoInputEventException {
        String input = null;
        int i = 0;
        System.err.println("INPUT>");
        while (dtmfInputQueue.isEmpty() && i++ < 5) {
            try {
                // give some time for the input thread to provide data.
                Thread.sleep(1000);
                // System.out.println("waiting for input");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (!dtmfInputQueue.isEmpty()) {
            input = dtmfInputQueue.remove();
        } else {
            input = new ConsoleInput(5, TimeUnit.SECONDS).readLine();
        }
        System.out.println("INPUT:" + input);
        return input;
    }
}
