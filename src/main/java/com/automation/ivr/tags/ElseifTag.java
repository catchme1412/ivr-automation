package com.automation.ivr.tags;

import com.automation.ivr.core.VXMLEngine;
import com.automation.ivr.exception.VxmlException;

public class ElseifTag extends LogicalTag {

    private String cond;

    @Override
    public void run() {
        if (cond != null && !cond.isEmpty()) {
            try {
                Boolean bool = (Boolean) VXMLEngine.getVxmlScriptEngine().eval(cond);
                System.out.println("Boolean " + bool);
                for (Tag tag : getChildTagList()) {
                    if (bool || tag instanceof ElseifTag || tag instanceof ElseTag) {
                        tag.run();
                    }
                }
            } catch (VxmlException e) {
                e.printStackTrace();
            }

        }
    }
}
