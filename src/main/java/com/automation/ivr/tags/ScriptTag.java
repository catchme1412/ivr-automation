package com.automation.ivr.tags;

import javax.script.ScriptException;

import com.automation.ivr.core.VXMLEngine;
import com.automation.ivr.exception.VxmlException;

public class ScriptTag extends AbstractTag {

    private String src;

    @Override
    public boolean execute() {
        try {
            if (src != null) {
                VXMLEngine.getVxmlScriptEngine().executeScriptFile(src);
            } else {
                VXMLEngine.getVxmlScriptEngine().executeScript(((TextTag) getChildTagList().get(0)).getText());
            }
        } catch (ScriptException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (VxmlException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
}
