package com.intecsec.java.basic.designpattern.command.receiver;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-11-09 21:58
 **/
public class TV {


    public void setVolume(int volume) {
        System.out.println("Set TV volume:" + volume);
    }

    public void on() {
        System.out.println("TV is On.");
    }

    public void off() {
        System.out.println("TV is Off.");
    }
}
