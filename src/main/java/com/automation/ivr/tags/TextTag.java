package com.automation.ivr.tags;

import org.w3c.dom.Node;

import com.automation.ivr.core.VXMLEngine;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextTag extends AbstractTag {

    String data;

    @Override
    public boolean execute() {

        try {
            VoiceManager vm = VoiceManager.getInstance();
            Voice voice = vm.getVoice("kevin16");
            voice.allocate();

            if (!data.isEmpty()) {
                VXMLEngine.getIoHandler().getOutputStack().add(data);
                if (!Boolean.valueOf(VXMLEngine.isOnMute())) {
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
