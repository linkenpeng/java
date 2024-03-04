package com.intecsec.java.springboot.simple.upload;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
public class FileUploadDTO {

    private boolean uploadComplete;

    private long mtime;

    private String path;

    private long size;

    private String fileExt;

    private String fileId;

    private Map<Integer, String> chunkMd5Info;
}
