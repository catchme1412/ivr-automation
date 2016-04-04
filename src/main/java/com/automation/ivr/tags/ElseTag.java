package com.automation.ivr.tags;

import java.util.List;

import org.w3c.dom.Node;

import com.automation.ivr.parser.VXMLFileParser;

public class ElseTag extends LogicalTag {

    @Override
    public void preExecute() {

        boolean isExecute = !isAllParentIfConditionsAreTrue();
        toggleExecute(isExecute);
    }

    public void setHierarchy(Node node, Tag tag, Tag parentTag) {
        this.parentTag = ((AbstractTag) parentTag);

        List<Tag> parentChildTagList = ((AbstractTag) this.parentTag).getChildTagList();
        parentChildTagList.add(tag);
        VXMLFileParser.stack.push(tag);
    }

}
