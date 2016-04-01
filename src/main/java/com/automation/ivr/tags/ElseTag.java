package com.automation.ivr.tags;

import java.util.List;

import org.w3c.dom.Node;

import com.automation.ivr.parser.VXMLFileParser;

public class ElseTag extends LogicalTag {

    @Override
    public void preExecute() {
        if (!isAllParentIfConditionsAreTrue()) {
           execute();
        }

    }

    public boolean execute() {
        for (Tag tag : getChildTagList()) {
            tag.preExecute();
        }
        return true;

    }

    public void setHierarchy(Node node, Tag tag, Tag parentTag) {
        this.parentTag = ((AbstractTag) parentTag);

        List<Tag> parentChildTagList = ((AbstractTag) this.parentTag).getChildTagList();
        parentChildTagList.add(tag);
        VXMLFileParser.stack.push(tag);
    }

}
