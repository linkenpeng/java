package com.intecsec.java.basic.designpattern.command;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-11-09 21:57
 **/
public interface Command {
    void execute();

    void undo();
}
