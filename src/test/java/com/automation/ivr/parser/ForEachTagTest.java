package com.automation.ivr.parser;

import java.io.File;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import junit.framework.TestCase;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.ivr.core.OutputWrapper;
import com.automation.ivr.core.VXMLEngine;

public class ForEachTagTest {

    private VXMLEngine vxmlEngine;

    @BeforeTest
    public void init() {
        VXMLEngine.setOnMute(true);
    }

    private void createFile(String fileName) {
        File file = new File(fileName);
        vxmlEngine = new VXMLEngine();
        vxmlEngine.setFile(file);
    }

    @Test
    public void testForEach() {
        Queue<String> output = new LinkedBlockingDeque<String>();
        output.add("Welcome to ABC Frozen Yogurt.");
        output.add("The current flavors of the week are");
        output.add("mango");
        output.add("cotton candy");
        output.add("apple pie");
        output.add("cheesecake");
        output.add("raspberry");
        output.add("Thank you for calling ABC Frozen Yogurt. We hope you stop by soon.");

        createFile("examples/foreach_example.vxml");
        vxmlEngine.main(null);
        Queue<OutputWrapper> outputQueue = VXMLEngine.getIoHandler().getOutputQueue();
        
        assertQueue(output, outputQueue );
        
    }

    private void assertQueue(Queue<String> expectedQueue, Queue<OutputWrapper> actualQueue) {
        while (!expectedQueue.isEmpty()) {
            String expectedStr = expectedQueue.remove();
            String actualStr = (String)actualQueue.remove().getOutput();
            TestCase.assertEquals(expectedStr, actualStr);
        }
    }

    @Test
    public void testForEach2() {

        createFile("examples/foreach_example_2.vxml");
        vxmlEngine.main(null);
    }
}
