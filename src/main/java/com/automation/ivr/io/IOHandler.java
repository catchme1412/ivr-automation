package com.automation.ivr.io;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import com.automation.ivr.core.OutputWrapper;

public class IOHandler {

    private Queue<OutputWrapper> outputQueue;
    private Queue<String> inputQueue;

    public IOHandler() {
        outputQueue = new LinkedBlockingDeque<OutputWrapper>();
        inputQueue = new LinkedBlockingDeque<String>();
    }

    public Queue<OutputWrapper> getOutputQueue() {
        return outputQueue;
    }

    public void setOutputQueue(Queue<OutputWrapper> outputQueue) {
        this.outputQueue = outputQueue;
    }

    public Queue<String> getInputQueue() {
        return inputQueue;
    }

    public void setInputQueue(Queue<String> inputQueue) {
        this.inputQueue = inputQueue;
    }


}
