package com.automation.ivr.tags;

import java.io.BufferedReader;
import java.io.IOException;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Prompt implements Tag {

    @Override
    public boolean execute(BufferedReader br) {

        String line;
        try {
            VoiceManager vm = VoiceManager.getInstance();
            Voice voice = vm.getVoice("kevin16");
            voice.allocate();

            line = br.readLine();
            voice.speak(line);
            System.out.println("Prompt:: " + line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
