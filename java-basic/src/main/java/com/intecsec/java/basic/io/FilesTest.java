package com.intecsec.java.basic.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-08 22:23
 **/
public class FilesTest {
    public static void main(String[] args) {
        moveFile();
        fileAttributes();
        createDirectory();
    }

    public static void moveFile() {
        Path from = Paths.get("/tmp", "test.txt");
        if(!Files.exists(from)) {
            try {
                Files.createFile(from);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Path to = from.getParent().resolve("test1.txt");
        try {
            System.out.println(Files.size(from));
            Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fileAttributes() {
        Path path = Paths.get("/tmp");
        System.out.println(Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS));
        try {
            BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
            System.out.println(basicFileAttributes.isDirectory());
            System.out.println(new Date(basicFileAttributes.lastModifiedTime().toMillis()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createDirectory() {
        Path path = Paths.get("/tmp/test");
        try {
            Files.deleteIfExists(path);
            if(!Files.exists(path)) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Path path2 = path.resolve("a.java");
        Path path3 = path.resolve("b.java");
        Path path4 = path.resolve("c.txt");
        Path path5 = path.resolve("d.jpg");
        try {
            Files.createFile(path2);
            Files.createFile(path3);
            Files.createFile(path4);
            Files.createFile(path5);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);
            for(Path p : paths) {
                System.out.println(p.getFileName());
            }
            System.out.println("================================================");
            DirectoryStream<Path> pathFilters = Files.newDirectoryStream(path, "*.{java,txt}");
            for(Path p : pathFilters) {
                System.out.println(p.getFileName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
