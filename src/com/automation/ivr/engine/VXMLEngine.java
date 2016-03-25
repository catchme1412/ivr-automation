package com.automation.ivr.engine;

import java.io.File;
import java.util.List;

import com.automation.ivr.exception.FileParsingException;
import com.automation.ivr.parser.VXMLFileParser;
import com.automation.ivr.tags.Tag;

public class VXMLEngine {

    public static void main(String[] args) {

        VXMLFileParser vxmlFileParser = new VXMLFileParser("", null);
        /*
         * vxmlFileParser.setFileName("examples/prompt_example.vxml");
         * vxmlFileParser.parse();
         */

        File file = new File("examples/prompt_example.vxml");
        try {
            List<Tag> tagList = vxmlFileParser.parse(file);
            for (Tag tag : tagList) {
//                tag.execute(br)
            }
        } catch (FileParsingException e) {
            e.printStackTrace();
        }

    }
}
