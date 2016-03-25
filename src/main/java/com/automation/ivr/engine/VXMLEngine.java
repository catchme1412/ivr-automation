package com.automation.ivr.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;

import com.automation.ivr.exception.FileParsingException;
import com.automation.ivr.parser.VXMLFileParser;
import com.automation.ivr.tags.Tag;

public class VXMLEngine {

    private File file;
    public static  Properties properties;
    static {
        properties = new Properties();
    }

    public void main(String[] args) {

        VXMLFileParser vxmlFileParser = new VXMLFileParser("", null);
        /*
         * vxmlFileParser.setFileName("examples/prompt_example.vxml");
         * vxmlFileParser.parse();
         */

//        file = new File("examples/prompt_example.vxml");
        try {
            File propertiesFile = new File("ivr-automation.properties");
            FileInputStream fileInput = new FileInputStream(propertiesFile);
            properties.load(fileInput);
            fileInput.close();

            List<Tag> tagList = vxmlFileParser.parse(file);
            for (Tag tag : tagList) {
                tag.execute();
            }
        } catch (FileParsingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
