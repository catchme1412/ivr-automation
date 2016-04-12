package com.automation.ivr.tags;

import org.w3c.dom.Node;

import com.automation.ivr.core.OutputType;
import com.automation.ivr.core.OutputWrapper;
import com.automation.ivr.core.VXMLEngine;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextTag extends AbstractTag {

    private String data;

    @Override
    public boolean execute() {

        try {
            VoiceManager vm = VoiceManager.getInstance();
            Voice voice = vm.getVoice("kevin16");
            voice.allocate();

            if (!data.isEmpty() && (!(((AbstractTag)this).parentTag instanceof ScriptTag))) {
                OutputWrapper outputWrapper = new OutputWrapper(OutputType.TTS, data);
                VXMLEngine.getIoHandler().getOutputQueue().add(outputWrapper);
                if (!Boolean.valueOf(VXMLEngine.isUnitTestCaseModeEnabled())) {
                    voice.speak(data);
                }
                System.out.println("Prompt:: " + data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    public String getText() {
        return data;
    }

    @Override
    public void setNonFieldData(Node node) {
        data = node.getTextContent().trim();
    }

}
