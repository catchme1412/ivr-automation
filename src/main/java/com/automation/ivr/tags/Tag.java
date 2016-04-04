package com.automation.ivr.tags;

import org.w3c.dom.Node;

public interface Tag {

    public void preExecute();

    public boolean execute();

    public void setNonFieldData(Node node);

    public void setHierarchy(Node node, Tag tag, Tag parentTag);

    public void cleanUp();

    public void postExecute();
}
