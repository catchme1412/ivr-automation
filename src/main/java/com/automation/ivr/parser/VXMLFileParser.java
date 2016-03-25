package com.automation.ivr.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.automation.ivr.engine.VXMLEngine;
import com.automation.ivr.exception.FileParsingException;
import com.automation.ivr.tags.Tag;
import com.automation.ivr.tags.TagFactory;

public class VXMLFileParser implements IFileParser {

    private String fileName;
    private String remoteLoc;

    public VXMLFileParser(String remoteLoc, VXMLEngine vxmlEngine) {
        this.remoteLoc = remoteLoc;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void parse() {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("<?") || line.trim().startsWith("</")) {
                    continue;
                }
                String className = getTag(line);
                Tag tag = (Tag) Class.forName("com.automation.ivr.tags." + className).newInstance();
                tag.execute();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private String getTag(String line) {

        StringBuffer tagName = new StringBuffer();
        char[] lineCharArray = line.trim().toCharArray();
        int i = 0;
        line.toCharArray();
        boolean started = false;
        while (i < line.length()) {
            if (lineCharArray[i] == '<') {
                started = true;
                i++;
                continue;
            }
            if (lineCharArray[i] == ' ' || (lineCharArray[i] == '/' && lineCharArray[i + 1] == '>')
                    || lineCharArray[i] == '>') {
                if (started) {
                    break;
                }
                i++;
                continue;
            }
            tagName.append(lineCharArray[i]);
            i++;
        }
        return (tagName.substring(0, 1).toUpperCase() + tagName.substring(1));
        /*
         * String endString = ">"; if (line.contains("/>")) { endString = "/>";
         * } return line.substring(line.indexOf("<"), line.indexOf(endString));
         */
    }

    public List<Tag> parse(File file) throws FileParsingException {

        List<Tag> tagList = new ArrayList<Tag>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            Tag tag = TagFactory.get(doc.getFirstChild());
            tagList.add(tag);
            
            parseAllNodes(tagList, doc.getFirstChild());
            /*NodeList nodeList = doc.getFirstChild().getChildNodes();
            for (int count = 0; count < nodeList.getLength(); count++) {
                Node node = nodeList.item(count);
                tagList.add(getTag(node));
            }*/
        } catch (ParserConfigurationException e) {
            throwFileParsingException(file.getName(), e);
        } catch (SAXException e) {
            throwFileParsingException(file.getName(), e);
        } catch (IOException e) {
            throwFileParsingException(file.getName(), e);
        } catch (InstantiationException e) {
            throwFileParsingException(file.getName(), e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throwFileParsingException(file.getName(), e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throwFileParsingException(file.getName(), e);
            e.printStackTrace();
        }

        return tagList;

    }

    private void parseAllNodes(List<Tag> tagList, Node node) throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE || currentNode.getNodeType() == Node.TEXT_NODE) {
                Tag tag = TagFactory.get(currentNode);
                tag.setNonFieldData(currentNode);
                tagList.add(tag);
                parseAllNodes(tagList, currentNode);
            }
        }
    }

/*    private Tag getTag(Node node) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String nodeName = node.getNodeName();
        nodeName = nodeName.substring(0, 1).toUpperCase() + nodeName.substring(1);
        Tag tag = (Tag) Class.forName("com.automation.ivr.tags." + nodeName + "Tag").newInstance();
        if(node.hasAttributes()){
            NamedNodeMap namedNodeMap = node.getAttributes();
            namedNodeMap.item(0)
        }
        
        return tag;
    }*/

    private void throwFileParsingException(String fileName, Exception e) throws FileParsingException {
        throw new FileParsingException("Exception while Parsing the file: " + fileName, e);
    }
}
