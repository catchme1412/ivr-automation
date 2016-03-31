package com.automation.ivr.parser;

import java.io.File;
import java.util.List;

import org.w3c.dom.NodeList;

import com.automation.ivr.exception.FileParsingException;
import com.automation.ivr.tags.Tag;

public interface IFileParser {

    public Tag parse(File file) throws FileParsingException;

}
