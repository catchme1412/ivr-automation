package com.automation.ivr.parser;

import java.io.File;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.ivr.core.VXMLEngine;

public class ChoiceTagTest {

    private VXMLEngine vxmlEngine;

    @BeforeTest
    public void init() {
        VXMLEngine.setUnitTestCaseModeEnabled(false);
    }

    private void createFile(String fileName) {
        File file = new File(fileName);
        vxmlEngine = new VXMLEngine();
        vxmlEngine.setFile(file);
    }

    @Test
    public void testForEach() {
        createFile("/opt/orbitz/code/web-ivr/src/main/webapp/ivr/testing/sao.vxml");
        vxmlEngine.main(null);
    }
}
