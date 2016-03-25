package com.automation.ivr.tags;

import org.w3c.dom.Node;


public class BlockTag extends AbstractTag{

    String name;
    String expr;
    String cond;

    @Override
    public boolean execute() {
        return true;
    }

    public String getName() {
        return name;
    }

    public String getExpr() {
        return expr;
    }

    public String getCond() {
        return cond;
    }

}
