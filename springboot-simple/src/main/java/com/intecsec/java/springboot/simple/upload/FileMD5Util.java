package com.intecsec.java.springboot.simple.upload;

import com.intecsec.java.util.MD5Util;

public class FileMD5Util {
    public static String getFileMD5(File file) {
        return MD5Util.getMD5(file.getBytes());
    }
}
