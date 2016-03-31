package com.automation.ivr.tags;

import com.automation.ivr.core.VXMLEngine;
import com.automation.ivr.exception.VxmlException;

public class VarTag extends AbstractTag {

    private String name;
    private String expr;

    @Override
    public boolean execute() {
        if(expr !=null && !expr.isEmpty()){
            try {
                VXMLEngine.getVxmlScriptEngine().assignScriptVar(name, expr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        return true;

    }

    public String getName() {
        return name;
    }

    public String getExpr() {
        return expr;
    }

}
