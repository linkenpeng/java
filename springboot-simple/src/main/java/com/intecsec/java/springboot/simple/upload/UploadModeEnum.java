package com.intecsec.java.springboot.simple.upload;

public enum UploadModeEnum {
    RANDOM_ACCESS(1),
    MAPPED_BYTEBUFFER(2)
    ;

    int mode;

    UploadModeEnum(int mode) {
        this.mode = mode;
    }
}
