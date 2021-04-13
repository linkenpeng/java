package com.intecsec.java.springboot.excel;

import lombok.Data;

import java.util.Date;

/**
 * 基础数据类
 *
 **/
@Data
public class UploadData {

    private String string;

    private Date date;

    private Double doubleData;
}
