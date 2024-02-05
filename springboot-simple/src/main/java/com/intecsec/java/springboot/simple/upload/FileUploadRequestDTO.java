package com.intecsec.java.springboot.simple.upload;

import lombok.Data;

@Data
public class FileUploadRequestDTO {
    private long chunkSize;
    private File file;
    private Integer chunk;
    private long chunks;
    private String originalFilename;
    private String path;
    private String md5;
}
