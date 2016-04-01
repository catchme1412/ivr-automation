package com.automation.ivr.tags;

public class FieldTag extends AbstractTag {

    private String name;
    private String cond;
    private String expr;
    private String type;

    public String getName() {
        return name;
    }

    public String getCond() {
        return cond;
    }

    public String getExpr() {
        return expr;
    }

    public String getType() {
        return type;
    }

}