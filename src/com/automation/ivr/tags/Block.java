package com.automation.ivr.tags;

import java.io.BufferedReader;

public class Block implements Tag {

    @Override
    public boolean execute(BufferedReader br) {
        return true;
    }

}
