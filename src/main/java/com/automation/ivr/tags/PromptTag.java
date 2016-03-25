package com.automation.ivr.tags;


public class PromptTag extends AbstractTag {

    private String bargein;
    private String bargeintype;
    private String cond;
    private String count;
    private String timeout;
    private String name;
    private String expr;

    @Override
    public boolean execute() {
/*
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
        }*/
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
