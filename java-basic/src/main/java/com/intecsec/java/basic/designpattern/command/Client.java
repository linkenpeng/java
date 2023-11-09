package com.intecsec.java.basic.designpattern.command;

import com.intecsec.java.basic.designpattern.command.cmdimp.LightOffCommand;
import com.intecsec.java.basic.designpattern.command.cmdimp.LightOnCommand;
import com.intecsec.java.basic.designpattern.command.cmdimp.TVOffCommand;
import com.intecsec.java.basic.designpattern.command.cmdimp.TVOnCommand;
import com.intecsec.java.basic.designpattern.command.receiver.Light;
import com.intecsec.java.basic.designpattern.command.receiver.TV;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-11-09 22:00
 **/
public class Client {
    public static void main(String[] args) {
        Light light = new Light();
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        Invoker invoker = new Invoker();
        invoker.setCommand(1, lightOnCommand, lightOffCommand);
        invoker.on(1);

        TV tv = new TV();
        TVOnCommand tvOnCommand = new TVOnCommand(tv);
        TVOffCommand tvOffCommand = new TVOffCommand(tv);
        invoker.setCommand(2, tvOnCommand, tvOffCommand);
        invoker.on(2);

        invoker.off(2);
        invoker.off(1);
    }
}
