package com.intecsec.java.basic.io;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-08 22:15
 **/
public class PathTest {
    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("/tmp", "ab.txt");
        System.out.println(path.getNameCount());

        File file = new File("/tmp/ab.txt");
        Path pathOther = file.toPath();
        System.out.println(path.compareTo(pathOther));

        Path path3 = Paths.get("/tmp", "ab.txt");
        System.out.println(path3.toString());

        Path path4 = Paths.get("/tmp");
        System.out.println(path4.resolve("ab.txt"));

        if(Files.isReadable(path)) {
            System.out.println("isReadable");
        } else {
            System.out.println("notRead");
        }

    }
}
