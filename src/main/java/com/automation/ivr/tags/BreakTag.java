package com.automation.ivr.tags;

public class BreakTag extends AbstractTag {

    private String time = "200";
    private long timeValue;

    @Override
    public void preExecute() {
        timeValue = Long.valueOf(time.substring(0, time.indexOf("ms")));
    }

    @Override
    public boolean execute() {
        try {
            Thread.sleep(timeValue);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
}
