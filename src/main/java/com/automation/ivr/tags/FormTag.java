package com.automation.ivr.tags;


public class FormTag extends AbstractTag {

    private String id;

    @Override
    public boolean execute() {
        return true;
    }

    public String getId() {
        return id;
    }

}
