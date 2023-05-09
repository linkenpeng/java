package com.intecsec.java.basic.io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-10 07:47
 **/
public class FileZipTest {
    public static void main(String[] args) {
        String file = "/tmp/test.txt";
        String zipFile = "/tmp/test.zip";
        zipCompress(file, zipFile);
    }

    public static void zipCompress(String filePath, String zipFilePath) {
        try {
            File file = new File(filePath);
            File zipFile = new File(zipFilePath);
            InputStream inputStream = new FileInputStream(file);
            ZipOutputStream zipOutput = new ZipOutputStream(new FileOutputStream(zipFile));
            zipOutput.putNextEntry(new ZipEntry(file.getName()));
            zipOutput.setComment("single file zip");

            int temp = 0;
            while ((temp = inputStream.read()) != -1) {
                zipOutput.write(temp);
            }

            inputStream.close();
            zipOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipUnCompress(String zipFile) {

    }

}
