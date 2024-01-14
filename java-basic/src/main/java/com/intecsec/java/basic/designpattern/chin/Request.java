package com.intecsec.java.basic.designpattern.chin;

/**
 * @description:
 * @author: peter.peng
 * @create: 2024-01-14 10:15
 **/
public class Request {
    private String name;
    private RequestType type;

    public Request(String name, RequestType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public RequestType getType() {
        return type;
    }
}
