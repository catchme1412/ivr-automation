package com.automation.ivr.tags;

import java.util.List;

import org.w3c.dom.Node;

import com.automation.ivr.core.VXMLEngine;
import com.automation.ivr.exception.VxmlException;
import com.automation.ivr.parser.VXMLFileParser;

public class IfTag extends LogicalTag {

    private String cond;
    private Boolean booleanValue;

    @Override
    public void run() {
        if (cond != null && !cond.isEmpty()) {
            execute();
            System.out.println("Boolean " + booleanValue);
            for (Tag tag : getChildTagList()) {
                if (booleanValue || tag instanceof ElseifTag || tag instanceof ElseTag) {
                    tag.run();
                }
            }

        }
        clearTopIfCondition();
    }

    @Override
    public boolean execute() {/*
        if (cond != null && !cond.isEmpty()) {
            try {
                Boolean bool = (Boolean) VXMLEngine.getVxmlScriptEngine().eval(cond);
                System.out.println("Boolean : 123 " + bool);
            } catch (VxmlException e) {
                e.printStackTrace();
            }

        }*/
        try {
            booleanValue = (Boolean) VXMLEngine.getVxmlScriptEngine().eval(cond);
            ifConditionState(booleanValue);
        } catch (VxmlException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    public String getCond() {
        return cond;
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
        VXMLFileParser.stack.push(tag);
    }

    public void cleanUp() {
        if(VXMLFileParser.stack.peek().getClass().getName().equals(ElseTag.class.getName())){
            VXMLFileParser.stack.pop();
        }
        VXMLFileParser.stack.pop();
    }
}
