package com.intecsec.java.basic.io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
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
        // zip(file, zipFile);
        // unZip(zipFile);

        String fileDirectory = "/tmp/test";
        String mulZipFile = "/tmp/test_mul.zip";
        // multiZip(fileDirectory, mulZipFile);

        unZipMul(mulZipFile);
    }

    public static void multiZip(String filePath, String zipFilePath) {
        try {
            ZipOutputStream zipOutput = new ZipOutputStream(new FileOutputStream(zipFilePath));
            zipOutput.setComment("single file zip");

            int temp = 0;
            InputStream inputStream = null;
            File file = new File(filePath);
            if(file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    File filez = files[i];
                    inputStream = new FileInputStream(filez);
                    zipOutput.putNextEntry(new ZipEntry(file.getName() + File.pathSeparator
                    + filez.getName()));
                    System.out.println("正在压缩文件" + filez.getName());
                    while ((temp = inputStream.read()) != -1) {
                        zipOutput.write(temp);
                    }
                    inputStream.close();
                }
            }
            zipOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zip(String filePath, String zipFilePath) {
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

    public static void unZip(String zipFile) {
        try {
            File zip = new File(zipFile);
            ZipInputStream zipInputStream = null;
            zipInputStream = new ZipInputStream(new FileInputStream(zip));
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            System.out.println("压缩实体名称" + nextEntry.getName());

            File outFile = new File("/tmp/" + nextEntry.getName());
            OutputStream outputStream = new FileOutputStream(outFile);
            int temp = 0;
            while ((temp = zipInputStream.read()) != -1) {
                outputStream.write(temp);
            }

            zipInputStream.close();
            outputStream.close();

            System.out.println("unzip done");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void unZipMul(String zipFilePath) {
        try {
            File zip = new File(zipFilePath);
            File outFile = null;
            ZipFile zipFile = new ZipFile(zip);
            ZipInputStream zipInputStream = null;
            OutputStream out = null;
            InputStream input = null;
            ZipEntry entry = null;
            zipInputStream = new ZipInputStream(new FileInputStream(zip));

            while ((entry = zipInputStream.getNextEntry()) != null) {
                System.out.println("解压缩" + entry.getName());
                outFile = new File("/tmp/" + entry.getName());
                if(!outFile.getParentFile().exists()) {
                    outFile.getParentFile().mkdirs();
                }
                if(!outFile.exists()) {
                    if(entry.isDirectory()) {
                        outFile.mkdirs();
                        System.out.println("create directory");
                    } else {
                        outFile.createNewFile();
                        System.out.println("create new file");
                    }
                }
                if(!entry.isDirectory()) {
                    input = zipFile.getInputStream(entry);
                    out = new FileOutputStream(outFile);
                    int temp = 0;
                    while ((temp = input.read()) != -1) {
                        out.write(temp);
                    }
                    input.close();
                    out.close();
                }
            }

            zipInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
