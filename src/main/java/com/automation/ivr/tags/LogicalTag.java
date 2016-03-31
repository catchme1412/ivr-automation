package com.automation.ivr.tags;

import java.util.Iterator;
import java.util.Stack;

public class LogicalTag extends AbstractTag {

    private static Stack<Boolean> logicalConditionStateStack;

    static {
        logicalConditionStateStack = new Stack<Boolean>();
    }

    public void ifConditionState(Boolean isTrue) {
        logicalConditionStateStack.push(isTrue);
    }

    public void clearTopIfCondition() {
        logicalConditionStateStack.pop();
    }

    public boolean isAllParentIfConditionsAreTrue() {
        return logicalConditionStateStack.peek();
/*        Iterator<Boolean> itr = logicalConditionStateStack.iterator();
        while (itr.hasNext()) {
            Boolean top = itr.next();
            if (itr.hasNext()) {
                System.out.println(top);
                if (!top) {
                    return false;
                }
            }
        }
        return true;*/
    }

    @Override
    public boolean execute() {
        // TODO Auto-generated method stub
        return false;
    }

}
