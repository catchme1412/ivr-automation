package com.automation.ivr.core;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import com.automation.ivr.exception.FileParsingException;
import com.automation.ivr.io.IOHandler;
import com.automation.ivr.parser.VXMLFileParser;
import com.automation.ivr.tags.Tag;

public class VXMLEngine {

    private File file;
    private static boolean onMute;
    private static IOHandler ioHandler;
    private static VxmlScriptEngine vxmlScriptEngine;
    static {
        onMute = false;
        ioHandler = new IOHandler();
        vxmlScriptEngine = new VxmlScriptEngine();
    }

    public void main(String[] args) {

        VXMLFileParser vxmlFileParser = new VXMLFileParser("", null);
        /*
         * vxmlFileParser.setFileName("examples/prompt_example.vxml");
         * vxmlFileParser.parse();
         */

        // file = new File("examples/prompt_example.vxml");
        try {

            Tag tag = vxmlFileParser.parse(file);
            tag.preExecute();;

        } catch (FileParsingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

/*    private void execute(Tag tag){
        tag.execute();
        tag
    }*/
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static boolean isOnMute() {
        return onMute;
    }

    public static void setOnMute(boolean onMute) {
        VXMLEngine.onMute = onMute;
    }

    public static IOHandler getIoHandler() {
        return ioHandler;
    }

    public static void setIoHandler(IOHandler ioHandler) {
        VXMLEngine.ioHandler = ioHandler;
    }

    public static VxmlScriptEngine getVxmlScriptEngine() {
        return vxmlScriptEngine;
    }

    public static void setVxmlScriptEngine(VxmlScriptEngine vxmlScriptEngine) {
        VXMLEngine.vxmlScriptEngine = vxmlScriptEngine;
    }

}
