package com.automation.ivr.parser;

import java.io.File;
import java.util.Queue;

import junit.framework.TestCase;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.ivr.core.OutputWrapper;
import com.automation.ivr.core.VXMLEngine;

public class IfElseTest {

    private VXMLEngine vxmlEngine;

    @BeforeTest
    public void init() {
        File file = new File("examples/if_else_example.vxml");
        vxmlEngine = new VXMLEngine();
        vxmlEngine.setFile(file);
        VXMLEngine.setOnMute(true);
    }

    @Test
    public void test() {

        vxmlEngine.main(null);
        Queue<OutputWrapper> testStack = VXMLEngine.getIoHandler().getOutputQueue();
        OutputWrapper outputString = testStack.poll();
//        sTestCase.assertEquals(outputString.getOutput(), "Hello World. This is my first prompt.");
    }
}
