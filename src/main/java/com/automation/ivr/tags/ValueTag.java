package com.automation.ivr.tags;

import com.automation.ivr.core.OutputType;
import com.automation.ivr.core.OutputWrapper;
import com.automation.ivr.core.VXMLEngine;
import com.automation.ivr.exception.VxmlException;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class ValueTag extends AbstractTag {

    private String expr;
    private String value;

    @Override
    public void preExecute() {
        try {
            value = (String) VXMLEngine.getVxmlScriptEngine().eval(expr);
        } catch (VxmlException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean execute() {

        if (expr != null && !expr.isEmpty()) {
            VoiceManager vm = VoiceManager.getInstance();
            Voice voice = vm.getVoice("kevin16");
            voice.allocate();
            OutputWrapper outputWrapper = new OutputWrapper(OutputType.TTS, value);
            VXMLEngine.getIoHandler().getOutputQueue().add(outputWrapper);
            if (!VXMLEngine.isOnMute()) {
                voice.speak(value);
            }
            System.out.println(value);

        }
        return true;
    }

    public String getExpr() {
        return expr;
    }

}
