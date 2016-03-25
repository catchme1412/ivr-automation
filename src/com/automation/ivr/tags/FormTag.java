package com.automation.ivr.tags;

import java.io.BufferedReader;

public class FormTag implements Tag {

    private String id;

    @Override
    public boolean execute(BufferedReader br) {
        return true;
    }

    public String getId() {
        return id;
    }

}
