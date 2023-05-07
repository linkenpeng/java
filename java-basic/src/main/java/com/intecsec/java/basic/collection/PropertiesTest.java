package com.intecsec.java.basic.collection;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-07 22:36
 **/
public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        String fileName = "test.properties";
        writeProperties(fileName, "name", "123456");
        getAllProperties(fileName);
        System.out.println(getValueByKey(fileName, "name"));
    }

    public static String getValueByKey(String filePath, String key) throws IOException {
        Properties properties = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        properties.load(in);
        String value = properties.getProperty(key);
        return value;
    }

    public static void getAllProperties(String filePath) throws IOException {
        Properties properties = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        properties.load(in);
        Enumeration enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String value = properties.getProperty(key);
            System.out.println("key: " + key + " value: " + value);
        }
    }

    public static void writeProperties(String filePath, String pKey, String pValue)
            throws IOException {
        File file = new File(filePath);
        if(!file.exists()) {
            file.createNewFile();
        }
        Properties properties = new Properties();
        FileInputStream in = new FileInputStream(file);
        properties.load(in);

        OutputStream out = new FileOutputStream(filePath);
        properties.setProperty(pKey, pValue);
        properties.store(out, "Update" + pKey + "name");
        out.close();
    }
}
