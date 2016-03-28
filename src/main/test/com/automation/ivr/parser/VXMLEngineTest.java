package com.automation.ivr.parser;

import java.io.File;
import java.util.Queue;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.automation.ivr.core.VXMLEngine;

public class VXMLEngineTest{

    private VXMLEngine vxmlEngine;

    @Before
    public void init() {
        File file = new File("examples/prompt_example.vxml");
        vxmlEngine = new VXMLEngine();
        vxmlEngine.setFile(file);
    }

    @Test
    public void test() {

        vxmlEngine.main(null);
        Queue<String> testStack = VXMLEngine.getIoHandler().getOutputStack();
        String outputString = testStack.poll();
        TestCase.assertEquals(outputString, "Hello World. This is my first prompt.");
    }

}
