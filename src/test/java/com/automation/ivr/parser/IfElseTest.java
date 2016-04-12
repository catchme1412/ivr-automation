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

        VXMLEngine.setUnitTestCaseModeEnabled(true);
    }

    private void createFile(String fileName) {
        File file = new File(fileName);
        vxmlEngine = new VXMLEngine();
        vxmlEngine.setFile(file);
    }

    @Test
    public void testIfElse() {
        createFile("examples/if_else_example.vxml");

        vxmlEngine.main(null);
        Queue<OutputWrapper> testStack = VXMLEngine.getIoHandler().getOutputQueue();
        OutputWrapper output = testStack.poll();
//        TestCase.assertEquals("Flavor is other", output.getOutput());
        
    }

    @Test
    public void testIfElseIf() {
        createFile("examples/if_elseif_else_example.vxml");
        vxmlEngine.main(null);
        Queue<OutputWrapper> testStack = VXMLEngine.getIoHandler().getOutputQueue();
        OutputWrapper output = testStack.poll();
        TestCase.assertEquals("Flavor is strawberry", output.getOutput());
    }
    
    @Test
    public void testNestedIf() {
        createFile("examples/nested_if_example.vxml");
        vxmlEngine.main(null);
        Queue<OutputWrapper> testStack = VXMLEngine.getIoHandler().getOutputQueue();
//        OutputWrapper output = testStack.poll();
//        TestCase.assertEquals("Flavor is strawberry", output.getOutput());
    }
}
