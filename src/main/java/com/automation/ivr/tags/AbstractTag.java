package com.automation.ivr.tags;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

import com.automation.ivr.parser.VXMLFileParser;

public abstract class AbstractTag implements Tag {

    private List<Tag> childTagList;
    protected Tag parentTag;

    public AbstractTag() {
        childTagList = new ArrayList<Tag>();
    }

    @Override
    public void run() {
        execute();
        for (Tag tag : childTagList) {
            tag.run();
        }
    }

/*    @Override
    public boolean execute() {

        return true;
    }*/

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
}
