package com.automation.ivr.tags;

import java.io.File;

import javax.script.ScriptException;

import com.automation.ivr.core.VXMLEngine;
import com.automation.ivr.exception.VxmlException;
import com.automation.ivr.exception.VxmlNoInputEventException;

public class ChoiceTag extends AbstractTag {

    private String dtmf;
    private String expr;

    @Override
    public boolean execute() {
        String userInput = null;
        try {
            userInput = VXMLEngine.getIoHandler().readInput();
        } catch (VxmlNoInputEventException e) {
            e.printStackTrace();
        }

        if (userInput != null && dtmf.equals(userInput)) {
            System.out.println("USER CHOICE....." + userInput);
            String url = null;
            try {
                url = (String) VXMLEngine.getVxmlScriptEngine().eval(expr);
            } catch (VxmlException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // TODO May lead to OutofMemory or StackOverFlow as the remaining
            // tags in
            // this file may not be executed and may never be executed
            VXMLEngine vxmlEngine = new VXMLEngine();
            File file = new File(url.substring(0, url.indexOf("?")));
            vxmlEngine.setFile(file);
            vxmlEngine.main(null);
        }
        return true;
    }
}
