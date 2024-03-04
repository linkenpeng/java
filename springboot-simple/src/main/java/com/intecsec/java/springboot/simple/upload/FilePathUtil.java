package com.intecsec.java.springboot.simple.upload;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.RandomAccessFile;

@Component
public class FilePathUtil {

    public String getPath(FileUploadRequestDTO param) {
        return param.getFile().getPath();
    }

}
