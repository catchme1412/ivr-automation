package com.automation.ivr.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.automation.ivr.tags.Tag;

public class VXMLFileParser {

    private String fileName;
    private VXMLEngine vxmlEngine;
    private String remoteLoc;

    public VXMLFileParser(String remoteLoc, VXMLEngine vxmlEngine) {
        this.remoteLoc = remoteLoc;
        this.vxmlEngine = vxmlEngine;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public VXMLEngine getVxmlEngine() {
        return vxmlEngine;
    }

    public void setVxmlEngine(VXMLEngine vxmlEngine) {
        this.vxmlEngine = vxmlEngine;
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
                tag.execute(br);
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
}
