package com.automation.ivr.tags;

import java.io.BufferedReader;

public class BlockTag implements Tag {

    String name;
    String expr;
    String cond;

    @Override
    public boolean execute(BufferedReader br) {
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
