package com.automation.ivr.parser;

import java.io.File;
import java.util.Queue;

import junit.framework.TestCase;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.ivr.core.OutputWrapper;
import com.automation.ivr.core.VXMLEngine;

public class VXMLEngineTest {

    private VXMLEngine vxmlEngine;

    @BeforeTest
    public void init() {
        File file = new File("examples/prompt_example.vxml");
        vxmlEngine = new VXMLEngine();
        vxmlEngine.setFile(file);
        VXMLEngine.setUnitTestCaseModeEnabled(true);
    }

    @Test
    public void test() {

        vxmlEngine.main(null);
        Queue<OutputWrapper> testStack = VXMLEngine.getIoHandler().getOutputQueue();
        OutputWrapper outputString = testStack.poll();
        TestCase.assertEquals(outputString.getOutput(), "Hello World. This is my first prompt.");
        outputString = testStack.poll();
        TestCase.assertEquals(outputString.getOutput(), "Hello World. This is my second prompt.");
    }

    @Test
    public void testIf() {
        File file = new File("examples/if_example.vxml");
        vxmlEngine.setFile(file);
        vxmlEngine.main(null);
        Queue<OutputWrapper> testStack = VXMLEngine.getIoHandler().getOutputQueue();
        OutputWrapper outputString = testStack.poll();

    }

}
