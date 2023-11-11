package com.intecsec.java.basic.designpattern.command.cmdimp;

import com.intecsec.java.basic.designpattern.command.Command;
import com.intecsec.java.basic.designpattern.command.receiver.Light;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-11-09 21:59
 **/
public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
