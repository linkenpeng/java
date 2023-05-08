package com.intecsec.java.basic.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-09 07:35
 **/
class Search implements FileVisitor {

    private final PathMatcher matcher;

    Search(String ext) {
       matcher = FileSystems.getDefault().getPathMatcher("glob:" + ext);
    }

    public void judgeFile(Path file) {
        Path name = file.getFileName();
        if(name != null && matcher.matches(name)) {
            try {
                System.out.println(name + " IN " + file.toRealPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
        judgeFile((Path) file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
        System.out.println("visitDirectory:" + dir);
        return FileVisitResult.CONTINUE;
    }
}

public class SearchJPGFiles {
    public static void main(String[] args) {
        String ext = "*.jpg";
        Path path = Paths.get("/tmp");
        Search walk = new Search(ext);
        EnumSet<FileVisitOption> followLinks = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        try {
            Files.walkFileTree(path, followLinks, Integer.MAX_VALUE, walk);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
