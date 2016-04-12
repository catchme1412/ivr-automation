package com.automation.ivr.core;

import java.io.File;

import com.automation.ivr.exception.FileParsingException;
import com.automation.ivr.io.IOHandler;
import com.automation.ivr.parser.VXMLFileParser;
import com.automation.ivr.tags.AbstractTag;
import com.automation.ivr.tags.Tag;

public class VXMLEngine {

    private File file;
    private static boolean unitTestCaseModeEnabled;
    private static IOHandler ioHandler;
    private static VxmlScriptEngine vxmlScriptEngine;
    static {
        unitTestCaseModeEnabled = false;
        ioHandler = new IOHandler();
        vxmlScriptEngine = new VxmlScriptEngine();
    }

    public void main(String[] args) {

        VXMLFileParser vxmlFileParser = new VXMLFileParser("", null);

        try {
            Tag tag = vxmlFileParser.parse(file);
            walk(tag);

        } catch (FileParsingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void walk(Tag tag) {
        tag.preExecute();
        ((AbstractTag) tag).tryExecute();
        for (Tag tagElement : ((AbstractTag) tag).getChildTagList()) {
            walk(tagElement);
        }
        tag.postExecute();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static boolean isUnitTestCaseModeEnabled() {
        return unitTestCaseModeEnabled;
    }

    public static void setUnitTestCaseModeEnabled(boolean onMute) {
        VXMLEngine.unitTestCaseModeEnabled = onMute;
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
