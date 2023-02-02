package com.intecsec.java.util;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter.Peng
 * Created on 2023/2/1 16:21
 */
public class Main {

    public static void main(String[] args) {
        List<NameValuePair> params = new ArrayList<>();
        String response = HttpUtil.get("http://localhost:8085/tid/get", params, "abc");
        System.out.println("response:" + response);
    }
}
