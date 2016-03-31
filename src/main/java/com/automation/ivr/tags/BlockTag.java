package com.automation.ivr.tags;


public class BlockTag extends AbstractTag {

    String name;
    String expr;
    String cond;

    public BlockTag() {
        super();
    }

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
