package com.intecsec.java.springboot.simple.upload;

public interface SliceUploadStrategy {

    FileUploadDTO sliceUpload(FileUploadRequestDTO param);
}
