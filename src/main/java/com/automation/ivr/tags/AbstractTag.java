package com.automation.ivr.tags;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.w3c.dom.Node;

import com.automation.ivr.parser.VXMLFileParser;

public abstract class AbstractTag implements Tag {

    private List<Tag> childTagList;
    protected Tag parentTag;
    private static Stack<Boolean> isExecuteTagStack;

    static {
        isExecuteTagStack = new Stack<Boolean>();
        isExecuteTagStack.push(true);
    }

    public AbstractTag() {
        childTagList = new ArrayList<Tag>();
    }

    @Override
    public void preExecute() {

    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void setNonFieldData(Node node) {
        // TODO Auto-generated method stub

    }

    public List<Tag> getChildTagList() {
        return childTagList;
    }

    public void setChildTagList(List<Tag> childTagList) {
        this.childTagList = childTagList;
    }

    public Tag getParentTag() {
        return parentTag;
    }

    public void setParentTag(Tag parentTag) {
        this.parentTag = parentTag;
    }

    public void setHierarchy(Node node, Tag tag, Tag parentTag) {
        Tag parTag;
        if (VXMLFileParser.stack.isEmpty()) {
            parTag = parentTag;
        } else {
            parTag = VXMLFileParser.stack.peek();
        }
        this.parentTag = parTag;
        List<Tag> parentChildTagList = ((AbstractTag) parTag).getChildTagList();
        parentChildTagList.add(tag);
    }

    public void cleanUp() {

    }

    public void tryExecute() {
        if (((AbstractTag) this).isExecutePeek()) {
            // System.out.println("EXECUTING:" + this + " | " + getDocumentURI()
            // );
            execute();
        } else {
            // System.out.println("SKIPPING:" + this);
        }
    }

    public boolean isExecutePeek() {
        return isExecuteTagStack.peek();
    }

    public void postExecute() {

    }

    public void toggleExecute(Boolean isTrue) {
        Boolean r = isExecuteTagStack.pop();
        isExecuteTagStack.push(isTrue);
    }

    public void setExecuteFlag(boolean isSkip) {
        isExecuteTagStack.push(isSkip);
    }

    public void clearTopExecuteFlag() {
        if (!isExecuteTagStack.isEmpty()) {
            isExecuteTagStack.pop();
        }
    }

}
