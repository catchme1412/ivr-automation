package com.automation.ivr.parser;


public class VXMLEngine {

    public static void main(String[] args) {

        VXMLFileParser vxmlFileParser = new VXMLFileParser("", null);
        vxmlFileParser.setFileName("examples/prompt_example.vxml");
        vxmlFileParser.parse();

    }
}
