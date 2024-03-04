package com.intecsec.java.springboot.simple.upload;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@Component
public class FileUtil {
    public static void close(RandomAccessFile file) {
        try {
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(FileChannel file) {
        try {
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void freedMappedByteBuffer(MappedByteBuffer mappedByteBuffer) {
        mappedByteBuffer.clear();
    }

    public static  byte[] readFileToByteArray(File confFile) {
        try {
            FileInputStream fis = new FileInputStream(confFile);
            byte[] bytes = new byte[(int) confFile.length()];
            fis.read(bytes); // 读取整个文件到字节数组
            fis.close();
            return bytes;
        } catch (Exception e) {
          return null;
        }
    }

    public static String getExtension(String toFileNewName) {
        return new File(toFileNewName).getParent();
    }

    public static String withoutHeadAndTailDiagonal(String path) {
        return path;
    }
}
