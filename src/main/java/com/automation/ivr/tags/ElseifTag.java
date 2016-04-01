package com.automation.ivr.tags;

import java.util.List;

import org.w3c.dom.Node;

import com.automation.ivr.core.VXMLEngine;
import com.automation.ivr.exception.VxmlException;
import com.automation.ivr.parser.VXMLFileParser;

public class ElseifTag extends LogicalTag {

    private String cond;
    private Boolean booleanValue;

    @Override
    public void preExecute() {
        if (cond != null && !cond.isEmpty()) {
            try {
                booleanValue = (Boolean) VXMLEngine.getVxmlScriptEngine().eval(cond);
                if (!isAllParentIfConditionsAreTrue()) {
                    clearTopIfCondition();
                    ifConditionState(booleanValue );
                    execute();
                }
            } catch (VxmlException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean execute() {
        System.out.println("Boolean " + booleanValue);
        for (Tag tag : getChildTagList()) {
            if (booleanValue || tag instanceof ElseifTag || tag instanceof ElseTag) {
                tag.preExecute();
            }
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
