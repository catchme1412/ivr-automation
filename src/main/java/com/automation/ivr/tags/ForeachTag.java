package com.automation.ivr.tags;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.script.ScriptException;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import jdk.nashorn.internal.objects.NativeArray;

import com.automation.ivr.core.VXMLEngine;

public class ForeachTag extends AbstractTag {

    String item;
    String array;

    @Override
    public void preExecute() {

        try {
            VXMLEngine.getVxmlScriptEngine().executeScript("var " + item);
            VXMLEngine.getVxmlScriptEngine().executeScript("var " + array);
            setExecuteFlag(true);
        } catch (ScriptException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean execute() {
        try {
            Object arr = VXMLEngine.getVxmlScriptEngine().executeScript(array);
            if (arr instanceof List) {
                for (Object o : (List) arr) {
                    VXMLEngine.getVxmlScriptEngine().assignScriptVar(item, o);
                    executeChildTree(this);
                }
            } else if(arr instanceof ScriptObjectMirror){
                Set<Entry<String, Object>> attrs = ((ScriptObjectMirror) arr).entrySet();
                for (Map.Entry<String, Object> attr : attrs) {
                    VXMLEngine.getVxmlScriptEngine().assignScriptVar(item, attr.getValue());
                    executeChildTree(this);
                }
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        toggleExecute(false);
        return true;

    }

    @Override
    public void postExecute() {
        clearTopExecuteFlag();
    }
}
