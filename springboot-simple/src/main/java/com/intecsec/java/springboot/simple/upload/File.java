package com.intecsec.java.springboot.simple.upload;

import lombok.Data;

@Data
public class File {

    private byte[] bytes;

    private String originalFilename;

    private String path;

}
