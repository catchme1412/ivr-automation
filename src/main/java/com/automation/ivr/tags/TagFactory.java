package com.automation.ivr.tags;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.w3c.dom.Node;

public class TagFactory {

    public static Tag get(Node node) {
//      String tagName = getTagName(node);
        try {
            Tag tag = getTag(node);
//            Constructor c = Class.forName(tagName).getConstructor();
//            Tag tag = (Tag) c.newInstance();
//            tag.setNode(node);
            if (tag != null) {
                setFieldValues(node, tag);
            }
            return tag;
        } catch (Exception e) {
            System.err.println("NO CLASS:" + e.getMessage());
            return new DoNothingTag();
        }
    }

    private static void setFieldValues(Node node, Tag tag) throws IllegalAccessException {
        Field[] fields = tag.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Node value = node.getAttributes() != null ? node.getAttributes().getNamedItem(field.getName()) : null;
            if (value != null) {
                field.set(tag, value.getNodeValue());
            }
        }
    }

    /*private static String getTagName(Node node) {
        String nodeName = node.getNodeName();
        if (nodeName.equals("cisco-data")) {
            nodeName = "data";
        }
        if (nodeName.startsWith("#")) {
            nodeName = nodeName.substring(1);
        }
        if (node.getNodeType() == Node.CDATA_SECTION_NODE) {
            nodeName = "CDATASection";
        }
        if (nodeName.contains("-")) {
            nodeName = nodeName.replaceAll("-", "");
        }
        return "com.vxml.tag." + Character.toUpperCase(nodeName.charAt(0)) + nodeName.substring(1) + "Tag";
    }*/

    private static Tag getTag(Node node) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String nodeName = node.getNodeName();
        Tag tag = null;
        nodeName = Character.toUpperCase(nodeName.charAt(0)) + nodeName.substring(1);
        if (node.getNodeType() == Node.TEXT_NODE) {
            nodeName = "Text";
        }
        tag = (Tag) Class.forName("com.automation.ivr.tags." + nodeName + "Tag").newInstance();
/*        if(node.hasAttributes()){
            NamedNodeMap namedNodeMap = node.getAttributes();
            namedNodeMap.item(0)
        }*/
        
        return tag;
    }
}
