package com.automation.ivr.tags;

import java.io.BufferedReader;
import java.io.IOException;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class PromptTag implements Tag {

    private String bargein;
    private String bargeintype;
    private String cond;
    private String count;
    private String timeout;
    private String name;
    private String expr;

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

    public String getBargein() {
        return bargein;
    }

    public String getBargeintype() {
        return bargeintype;
    }

    public String getCond() {
        return cond;
    }

    public String getCount() {
        return count;
    }

    public String getTimeout() {
        return timeout;
    }

    public String getName() {
        return name;
    }

    public String getExpr() {
        return expr;
    }

}
