package com.intecsec.java.basic.io;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-08 21:58
 **/
public class FileTest {
    public static void main(String[] args) {
        String dirPath = "/tmp/a";
        File d = new File(dirPath);
        if(!d.exists()) {
            d.mkdirs();
        }
        System.out.println(d.isDirectory());

        String filePath = "/tmp/a/test.txt";
        File f = new File(filePath);
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(f.isFile());
        System.out.println(f.getName());
        System.out.println(f.getParent());
        System.out.println(f.getPath());
        System.out.println(f.length());
        System.out.println(f.lastModified());

        File[] fs = d.listFiles();
        for(File ff : fs) {
            System.out.println(ff.getPath());
        }

        f.delete();
        d.delete();
    }
}
