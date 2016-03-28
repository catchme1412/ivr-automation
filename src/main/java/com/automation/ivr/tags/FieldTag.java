package com.automation.ivr.tags;

public class FieldTag extends AbstractTag {

    private String name;
    private String cond;
    private String expr;

    @Override
    public boolean execute() {
        return true;

    }

    public String getName() {
        return name;
    }

    public String getCond() {
        return cond;
    }

    public String getExpr() {
        return expr;
    }

}