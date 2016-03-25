package com.automation.ivr.tags;

import org.w3c.dom.Node;

public interface Tag {

    public boolean execute();

    public void setNonFieldData(Node node);
}
